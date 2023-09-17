package com.utn.ejer01.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String estado;


    private Date fecha;



    private String tipoEnvio;


    private double total;


    @OneToOne(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;



    @OneToMany(cascade = CascadeType.REFRESH,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="pedido_id")

    @Builder.Default
    private List<DetallePedido> Detallespedido= new ArrayList<>();
    public void setDetallespedido(DetallePedido detallespedido) {
        this.Detallespedido.add(detallespedido);
    }
}
