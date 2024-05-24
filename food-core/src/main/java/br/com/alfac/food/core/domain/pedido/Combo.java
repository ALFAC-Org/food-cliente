package br.com.alfac.food.core.domain.pedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.alfac.food.core.domain.item.CategoriaItem;
import br.com.alfac.food.core.domain.item.Item;
import br.com.alfac.food.core.exception.FoodError;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.combo.ComboError;
import br.com.alfac.food.core.exception.item.ItemError;
import br.com.alfac.food.core.utils.CollectionsUtils;

public class Combo {
    private Lanche lanche;
    private Item acompanhamento;
    private Item bebida;
    private Item sobremesa;

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public Item getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Item acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Item getBebida() {
        return bebida;
    }

    public void setBebida(Item bebida) {
        this.bebida = bebida;
    }

    public Item getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(Item sobremesa) {
        this.sobremesa = sobremesa;
    }

    public List<Item> getItens() {
        List<Item> itens = new ArrayList<>();
        if (lanche != null) {
            itens.add(lanche);
        }
        if (acompanhamento != null) {
            itens.add(acompanhamento);
        }
        if (bebida != null) {
            itens.add(bebida);
        }
        if (sobremesa != null) {
            itens.add(sobremesa);
        }
        return itens;
    }
    public void validarItens() throws FoodException {

        if (CollectionsUtils.vazio(getItens())) {
            throw new FoodException(ComboError.COMBO_VAZIO);
        }

        List<FoodError> erros = new ArrayList<>(getErrosLance(lanche));
        erros.add(getErro(acompanhamento, CategoriaItem.ACOMPANHAMENTO, ItemError.CATEGORIA_ITEM_ACOMPANHAMENTO_INVALIDA));
        erros.add(getErro(bebida, CategoriaItem.BEBIDA, ItemError.CATEGORIA_ITEM_BEBIDA_INVALIDA));
        erros.add(getErro(sobremesa, CategoriaItem.SOBREMESA, ItemError.CATEGORIA_ITEM_SOBREMESA_INVALIDA));

        erros.removeIf(Objects::isNull);

        if (CollectionsUtils.naoVazio(erros)) {
            throw new FoodException(erros);
        }
    }

    private FoodError getErro(final Item itemValidacao, final CategoriaItem categoriaItemEsperada, final FoodError error) {

        if (Objects.nonNull(itemValidacao) && categoriaItemNaoPermitida(categoriaItemEsperada, itemValidacao.getCategoria())) {
            return error;
        }

        return null;
    }

    private List<FoodError> getErrosLance(final Lanche lanche) {
        List<FoodError> erros = new ArrayList<>();
        if (lanche != null) {
            if (categoriaItemNaoPermitida(CategoriaItem.LANCHE, lanche.getCategoria())) {
                erros.add(ItemError.CATEGORIA_ITEM_LANCHE_INVALIDA);
            }
            if (CollectionsUtils.naoVazio(lanche.getComplementos())) {
                lanche.getComplementos().forEach(complemento -> erros.add(getErro(complemento, CategoriaItem.COMPLEMENTO, ItemError.CATEGORIA_ITEM_COMPLEMENTO_INVALIDA)));
            }
        }

        return erros;
    }

    private boolean categoriaItemNaoPermitida(CategoriaItem categoriaEsperada, CategoriaItem categoriaItem) {
        return !categoriaEsperada.equals(categoriaItem);
    }

}