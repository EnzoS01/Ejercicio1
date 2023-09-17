package com.utn.ejer01.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;



    private String apellido;


    private String telefono;


    private String email;

    @OneToMany(cascade = CascadeType.REFRESH,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")

    @Builder.Default
    private List<Pedido> pedidos= new ArrayList<>();
    public void setPedidos(Pedido pedido) {
        this.pedidos.add(pedido);
    }


    @OneToMany(cascade = CascadeType.REFRESH,orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")

    @Builder.Default
    private List<Domicilio> domicilios= new ArrayList<>();
    public void setDomicilios(Domicilio domicilio) {
        this.domicilios.add(domicilio);
    }
}
