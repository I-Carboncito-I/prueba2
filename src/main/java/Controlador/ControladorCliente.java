package Controlador;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ClassClienteImp;
import model.TblCliente;

/**
 * Servlet implementation class ControladorCliente
 */
public class ControladorCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorCliente() {
        super();
        // TODO Auto-generated constructor stub
    }   //fin del constrolador

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		TblCliente cliente=new TblCliente();
		ClassClienteImp crud=new ClassClienteImp();
		List<TblCliente> listadocliente=crud.ListadoCliente();
		//invocamos el listado  de productos para la vista
		request.setAttribute("listadodeproductos",listadocliente);
		//redireccionamos
		//request.getRequestDispatcher("/ListadoClientes.jsp").forward(request, response);
		
		//recuperamos la accion y codigo
		String accion=request.getParameter("accion");
		//aplicamos una condicion...
		if(accion!=null){
			switch(accion){
			case "Modificar":
				int codigo=Integer.parseInt(request.getParameter("cod"));
				//asignar el codigo...
				cliente.setIdcliente(codigo);
				TblCliente buscarcod=crud.BuscarCliente(cliente);
				//enviar los valores buscados por codigo de la base de datos
				//al formulario actualizar..
				request.setAttribute("codigo",buscarcod.getIdcliente());
				request.setAttribute("nombre",buscarcod.getNombre());
				request.setAttribute("apellido",buscarcod.getApellido());
				request.setAttribute("dni",buscarcod.getDni());
				request.setAttribute("email",buscarcod.getEmail());
				request.setAttribute("telefono",buscarcod.getTelf());
				request.setAttribute("sexo",buscarcod.getSexo());
				request.setAttribute("nacionalidad",buscarcod.getNacionalidad());
				//redireccionar..
				request.getRequestDispatcher("/FormActualizarCliente.jsp").forward(request, response);
				//salimos
				break;
				
			
			 }  //fin del switch...
			
			
		}   //fin del if...
			
		
	} //fin del metodo doget..

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//recuperamos los valores del formulario...
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		String dni=request.getParameter("dni");
		String email=request.getParameter("email");
		String telef=request.getParameter("telefono");
		String sexo=request.getParameter("sexo");
		String nacionalidad=request.getParameter("nacionalidad");
		
		//instanciar las respectivas entidades...
		TblCliente cliente=new TblCliente();
		ClassClienteImp crud=new ClassClienteImp();
		//asignamos valores
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setDni(dni);
		cliente.setEmail(email);
		cliente.setTelf(telef);
		cliente.setSexo(sexo);
		cliente.setNacionalidad(nacionalidad);
		//invocamos la metodo registrar...
		crud.RegistrarCliente(cliente);
		//actualizador listado de clientes
		List<TblCliente> listadocliente=crud.ListadoCliente();
		//invocamos el listado  de productos para la vista
		request.setAttribute("listadodeproductos",listadocliente);
		//redireccionamos
		request.getRequestDispatcher("/ListadoClientes.jsp").forward(request, response);
		
		
			
		
		
		
		
	}  //fin del metodo dopost...

}
