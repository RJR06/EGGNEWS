package com.EGGNews.EGGNEWS.servicios;

import com.EGGNews.EGGNEWS.entidades.Imagen;
import com.EGGNews.EGGNEWS.entidades.Usuario;
import com.EGGNews.EGGNEWS.enumeraciones.Rol;
import com.EGGNews.EGGNEWS.excepciones.Exception1;
import com.EGGNews.EGGNEWS.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements UserDetailsService {
    @Autowired
    UsuarioRepositorio UR;
    @Autowired
    ImagenServicio IMS;

    @Transactional
    public void registrar(MultipartFile archivo, String nombreUsuario, String password, String password2) throws Exception1 {
        validar(nombreUsuario, password, password2);
        Imagen img = IMS.guardar(archivo);
        Usuario u = new Usuario();
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(new BCryptPasswordEncoder().encode(password));
        u.setEstado(true);
        u.setFecha_alta(new Date());
        u.setImagen(img);

        u.setRol(Rol.USER);

        UR.save(u);
    }

    @Transactional
    public void actualizar(MultipartFile archivo, Long idUsuario, String nombreUsuario, String password, String password2) throws Exception1 {
        validar(nombreUsuario, password, password2);

        Optional<Usuario> respuesta = UR.findById(idUsuario);

        if (respuesta.isPresent()) {
            Imagen img = IMS.guardar(archivo);
            Usuario u = new Usuario();
            u.setNombreUsuario(nombreUsuario);
            u.setPassword(new BCryptPasswordEncoder().encode(password));
            u.setEstado(true);
            u.setFecha_alta(new Date());
            u.setImagen(img);

            u.setRol(Rol.USER);

           Integer idImagen = null;

            if (u.getImagen() != null) {
                idImagen = u.getImagen().getId();
            }
            Imagen imagen = IMS.actualizar(archivo, idImagen);

            u.setImagen(imagen);

            UR.save(u);
        }

    }


    private void validar(String nombreUsuario, String password, String password2) throws Exception1 {

        if (nombreUsuario.isEmpty() || nombreUsuario == null) {
            throw new Exception1("El nombreUsuario no puede ser nulo o estar vacío");
        }

        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new Exception1("La contraseña no puede estar vacía, y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new Exception1("Las contraseñas ingresadas deben ser iguales");
        }
        if (UR.buscarPorNombreUsuario(nombreUsuario) != null) {
            throw new Exception1(("El nombre de usuario " + nombreUsuario + " ya se encuentro registrado"));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = UR.buscarPorNombreUsuario(username);
        if (u != null) {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + u.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", u);

            return new User(u.getNombreUsuario(), u.getPassword(), permisos);
        } else {
            return null;
        }
    }
}
