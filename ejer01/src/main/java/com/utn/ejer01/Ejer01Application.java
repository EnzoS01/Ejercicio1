package com.utn.ejer01;

import com.utn.ejer01.Repositorios.*;
import com.utn.ejer01.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class Ejer01Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ejer01Application.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo){

		return args -> {
			System.out.println("------------PROBANDO------------");
			Cliente cliente1= new Cliente();
			cliente1.setNombre("Ricardo");
			cliente1.setApellido("Aguilar");
			cliente1.setEmail("ra1@gmail.com");
			cliente1.setTelefono("234124128");



			Domicilio domicilio1= Domicilio.builder()
					.calle("Lavalle")
					.numero("1901")
					.localidad("Mendoza")
					.build();

			domicilioRepository.save(domicilio1);
			cliente1.setDomicilios(domicilio1);


			Domicilio domicilio2= Domicilio.builder()
					.calle("Patricias")
					.numero("2018")
					.localidad("Lujan")
					.build();
			domicilioRepository.save(domicilio2);
			cliente1.setDomicilios(domicilio2);


			Factura factura1= new Factura();
			factura1.setNumero(1);
			Date fecha1=new Date(2023,9,21);
			factura1.setFecha(fecha1);
			factura1.setDescuento(10);
			factura1.setFormaPago("efectivo");
			factura1.setTotal(1000);
			facturaRepository.save(factura1);

			DetallePedido detallePedido1= DetallePedido.builder()
					.cantidad(2)
					.subtotal(1000)
					.build();


			Pedido pedido1= new Pedido();
			pedido1.setEstado("entregado");
			pedido1.setFecha(fecha1);
			pedido1.setTipoEnvio("retira");
			pedido1.setTotal(1100);
			pedido1.setFactura(factura1);
			pedido1.setDetallespedido(detallePedido1);

			cliente1.setPedidos(pedido1);



			Producto producto1= new Producto();
			producto1.setTipo("manufacturado");
			producto1.setTiempoEstimadoCocina(10);
			producto1.setDenominacion("Pancho");
			producto1.setPrecioVenta(500);
			producto1.setPrecioCompra(300);
			producto1.setStockActual(10);
			producto1.setStockMinimo(5);
			producto1.setUnidadMedida("kg");
			producto1.setReceta("");


			detallePedido1.setProducto(producto1);
			Rubro rubro1= Rubro.builder()
					.denominacion("alimentos")
					.build();


			rubro1.setProductos(producto1);
			rubroRepository.save(rubro1);


			productoRepository.save(producto1);
			detallePedidoRepository.save(detallePedido1);
			pedidoRepository.save(pedido1);
			clienteRepository.save(cliente1);




			Pedido pedidoRecuperado= pedidoRepository.findById(pedido1.getId()).orElse(null);
			if(pedidoRecuperado != null){
				System.out.println("Estado: " + pedidoRecuperado.getEstado());
				System.out.println("Fecha: "+ pedidoRecuperado.getFecha());
				System.out.println("Tipo de envío: "+pedidoRecuperado.getTipoEnvio());
				System.out.println("Forma de pago: " + pedidoRecuperado.getFactura().getFormaPago());
				System.out.println("Total: "+pedidoRecuperado.getFactura().getTotal());
			}




			Cliente clienteRecuperado= clienteRepository.findById(cliente1.getId()).orElse(null);
			if(clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());

			}
		};


	}

}
