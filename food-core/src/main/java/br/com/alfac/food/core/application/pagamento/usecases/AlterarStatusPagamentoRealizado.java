package br.com.alfac.food.core.application.pagamento.usecases;

import br.com.alfac.food.core.application.pagamento.dto.PagamentoEntityDTO;
import br.com.alfac.food.core.application.pagamento.gateways.PagamentoRepository;
import br.com.alfac.food.core.application.pagamento.mapper.PagamentoMapper;
import br.com.alfac.food.core.domain.pagamento.Pagamento;
import br.com.alfac.food.core.exception.FoodException;
import br.com.alfac.food.core.exception.pagamento.PagamentoErro;

import java.util.Optional;

public class AlterarStatusPagamentoRealizado {


    private final PagamentoRepository pagamentoRepository;

    public AlterarStatusPagamentoRealizado(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }


    public PagamentoEntityDTO executar(final Long idPagamento) throws FoodException {


        Optional<PagamentoEntityDTO> pagamentoEntityOpt = pagamentoRepository.buscarPorId(idPagamento);

        PagamentoEntityDTO pagamentoEntityDTO = pagamentoEntityOpt.orElseThrow(() -> new FoodException(PagamentoErro.PAGAMENTO_NAO_ENCONTRADO));

        Pagamento pagamento = PagamentoMapper.toPagamento(pagamentoEntityDTO);
        pagamento.aprovar();

        PagamentoEntityDTO pagamentoEntityAtualizado = PagamentoMapper.toPagamentoEntityDTO(pagamento);

        return pagamentoRepository.atualizar(pagamentoEntityAtualizado);


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
