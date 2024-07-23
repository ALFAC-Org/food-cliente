package br.com.alfac.food.core.application.pagamento.usecases;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.core.application.pagamento.mapper.PagamentoMapper;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;

public class CriarPagamentoPendente {


    private final PagamentoRepository pagamentoRepository;

    public CriarPagamentoPendente(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }


    public PagamentoEntityDTO executar(final Long idPedido) throws FoodException {

        Pagamento pagamento = new Pagamento(idPedido);

        return pagamentoRepository.criar(PagamentoMapper.toPagamentoEntityDTO(pagamento));

//        PedidoDTO pedidoDTO = pedidoService.consultarPedidoPorId(idPedido);
//
//        if (!StatusPedido.AGUARDANDO_PAGAMENTO.equals(pedidoDTO.getStatusPedido())) {
//            throw new FoodException(PagamentoErro.PAGAMENTO_JA_REALIZADO);
//        }
//
//        boolean pagamentoRealizado = pagamentoClient.efetuarPagamento(pedidoDTO.getId());
//
//        if (pagamentoRealizado) {
//            pedidoDTO = statusPedidoPagamentoService.atualizarStatus(pedidoDTO);
//        }
//
//        return new PagamentoDTO(pedidoDTO.getId(), pagamentoRealizado, pedidoDTO.getStatusPedido());
    }
}
