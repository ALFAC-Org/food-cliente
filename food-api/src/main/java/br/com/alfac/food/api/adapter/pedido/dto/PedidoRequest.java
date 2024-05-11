package br.com.alfac.food.api.adapter.pedido.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;

public class PedidoRequest {
    private Long clienteId;
    private List<ComboRequest> combos;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ComboRequest> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboRequest> combos) {
        this.combos = combos;
    }

    public PedidoDTO toDTO() {

        PedidoDTO pedidoDTO = new PedidoDTO(); 
        List<ComboDTO> combosDTO = new ArrayList<>();

        if(combos != null){
            for(ComboRequest combo : combos){
                ComboDTO comboDTO = new ComboDTO();

                if(combo.getLanche() != null){
                    comboDTO.setLanche(combo.getLanche().toLancheDTO());
                }
                if(combo.getAcompanhamento() != null){
                    comboDTO.setAcompanhamento(combo.getAcompanhamento().toDTO());
                }
                if(combo.getBebida() != null){
                    comboDTO.setBebida(combo.getBebida().toDTO());
                }
                if(combo.getSobremesa() != null){
                    comboDTO.setSobremesa(combo.getSobremesa().toDTO());
                }

                combosDTO.add(comboDTO);
            }
        }

        pedidoDTO.setCombos(combosDTO);

        return pedidoDTO;
    }

}