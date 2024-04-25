package io.github.rafaelpeinado.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class ItemPedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
