package br.edu.ifpb.recdata.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.recdata.entidades.Item;
import br.edu.ifpb.recdata.service.ProviderServiceFactory;
import br.edu.ifpb.recdata.service.ReCDATAService;

@ManagedBean(name="listarItensBean")
@ViewScoped
public class ListarItensBean extends Item implements Serializable{

	private static final long serialVersionUID = -7386032833652667551L;

	private List<Item> itens = new ArrayList<Item>();
	
	ReCDATAService service = ProviderServiceFactory
			.createServiceClient(ReCDATAService.class);
	
	public String listarItens() {
		String navegacao = null;	

		Item item = new Item();
		item.setDescricao(getDescricao());
		this.itens = this.service.consultarItens(item);
		
		return navegacao;
	}

	public void reservarItem(Item item) {
		ReservarItemBean reservaItem = new ReservarItemBean(item);
		GenericBean.setSessionValue("reservarItemBean", reservaItem);
		
		GenericBean.resetSessionScopedBean("listarItensBean");
		
		reservaItem.redirecionarReservaItem();		
	}
	
	public void detalharItem(Item item) {
		
	}
	
	public List<Item> getItens() {
		return itens;
	}
}