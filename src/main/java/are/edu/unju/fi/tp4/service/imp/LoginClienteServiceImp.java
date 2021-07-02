package are.edu.unju.fi.tp4.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import are.edu.unju.fi.tp4.model.Cliente;
import are.edu.unju.fi.tp4.repository.IclienteDAO;
@Service
public class LoginClienteServiceImp implements UserDetailsService{
	@Autowired
	IclienteDAO clienteDAO;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Cliente clienteEncontrado=clienteDAO.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Cliente inválido"));
		List<GrantedAuthority> tipos = new ArrayList<>(); 
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(clienteEncontrado.getTipoDocumento());
		tipos.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(email, clienteEncontrado.getPassword(), tipos);
		
		return user;
	}

}
