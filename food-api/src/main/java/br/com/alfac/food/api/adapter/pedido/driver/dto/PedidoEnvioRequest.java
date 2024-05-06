package br.com.alfac.food.api.adapter.pedido.driver.dto;


import java.util.ArrayList;
import java.util.List;

import br.com.alfac.food.core.application.pedido.dto.AcompanhamentoDTO;
import br.com.alfac.food.core.application.pedido.dto.BebidaDTO;
import br.com.alfac.food.core.application.pedido.dto.ComboDTO;
import br.com.alfac.food.core.application.pedido.dto.ComplementoDTO;
import br.com.alfac.food.core.application.pedido.dto.LancheDTO;
import br.com.alfac.food.core.application.pedido.dto.PedidoDTO;
import br.com.alfac.food.core.application.pedido.dto.SobremesaDTO;

public class PedidoEnvioRequest {
    private List<Combo> combos;
    private Long clienteId;

    public List<Combo> getCombos() {
        return combos;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public PedidoDTO toDTO() {

        PedidoDTO pedidoDTO = new PedidoDTO(); 
        List<ComboDTO> combosDTO = new ArrayList<>();

        if(combos != null){
            for(Combo combo : combos){
                ComboDTO comboDTO = new ComboDTO();

                if(combo.getLanche() != null){
                    comboDTO.setLanche(combo.getLanche().toDTO());
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

    class Combo {
        private Lanche lanche;
        private Acompanhamento acompanhamento;
        private Bebida bebida;
        private Sobremesa sobremesa;

        public Lanche getLanche() {
            return lanche;
        }

        public void setLanche(Lanche lanche) {
            this.lanche = lanche;
        }

        public Acompanhamento getAcompanhamento() {
            return acompanhamento;
        }

        public void setAcompanhamento(Acompanhamento acompanhamento) {
            this.acompanhamento = acompanhamento;
        }

        public Bebida getBebida() {
            return bebida;
        }

        public void setBebida(Bebida bebida) {
            this.bebida = bebida;
        }

        public Sobremesa getSobremesa() {
            return sobremesa;
        }

        public void setSobremesa(Sobremesa sobremesa) {
            this.sobremesa = sobremesa;
        }
    }

    class Lanche {
        private int itemId;
        private List<Complemento> complementos;
        private String observacoes;

        public int getItemId() {
            return itemId;
        }
    
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public List<Complemento> getComplementos() {
            return complementos;
        }
    
        public void setComplementos(List<Complemento> complementos) {
            this.complementos = complementos;
        }
    
        public String getObservacoes() {
            return observacoes;
        }
    
        public void setObservacoes(String observacoes) {
            this.observacoes = observacoes;
        }

        public LancheDTO toDTO() {
    
            LancheDTO lancheDTO = new LancheDTO();
            List<ComplementoDTO> complementosDTO = new ArrayList<>();
    
            if(complementos != null){
                for(Complemento complemento : complementos){
                    complementosDTO.add(complemento.toDTO());
                }
            }
    
            lancheDTO.setItemId(itemId);
            lancheDTO.setObservacoes(observacoes);
            lancheDTO.setComplementos(complementosDTO);
    
            return lancheDTO;
        }
    }

    class Complemento {
        private int itemId;

        public int getItemId() {
            return itemId;
        }
    
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public ComplementoDTO toDTO() {    
            ComplementoDTO complementoDTO = new ComplementoDTO();
            complementoDTO.setItemId(itemId);
            return complementoDTO;
        }
    }

    class Acompanhamento {        
        private int itemId;

        public int getItemId() {
            return itemId;
        }
    
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public AcompanhamentoDTO toDTO() {    
            AcompanhamentoDTO acompanhamentoDTO = new AcompanhamentoDTO();
            acompanhamentoDTO.setItemId(itemId);
            return acompanhamentoDTO;
        }
    }

    class Bebida {        
        private int itemId;

        public int getItemId() {
            return itemId;
        }
    
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public BebidaDTO toDTO() {    
            BebidaDTO bebidaDTO = new BebidaDTO();
            bebidaDTO.setItemId(itemId);
            return bebidaDTO;
        }
    }

    class Sobremesa {        
        private int itemId;

        public int getItemId() {
            return itemId;
        }
    
        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public SobremesaDTO toDTO() {    
            SobremesaDTO sobremesaDTO = new SobremesaDTO();
            sobremesaDTO.setItemId(itemId);
            return sobremesaDTO;
        }
    }
}