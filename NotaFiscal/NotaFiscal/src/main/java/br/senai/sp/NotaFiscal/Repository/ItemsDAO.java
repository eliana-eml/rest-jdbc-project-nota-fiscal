package br.senai.sp.NotaFiscal.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.senai.sp.NotaFiscal.Model.Item;

@Repository
public class ItemsDAO { //-- Id_Item, Descricao, Valor, Quantidade, nr_nota
	
	private static final String INSERIR_ITEM = "INSERT INTO nota_fiscal.item(descricao, valor, qtd, nr_nota) VALUES (?,?,?,?)";
	private static final String ALTERAR_ITEM = "UPDATE nota_fiscal.item SET descricao = ?, valor = ?, qtd = ?, nr_nota = ? WHERE id_item = ?";
	private static final String BUSCAR_ITEMS = "SELECT id_item, descricao, valor, qtd, nr_nota FROM nota_fiscal.item";
	private static final String BUSCAR_ITEM_ID = "SELECT id_item, descricao, valor, qtd, nr_nota FROM nota_fiscal.item WHERE id_item = ?";
	private static final String BUSCAR_NR_NOTA = "SELECT id_item, descricao, valor, qtd, nr_nota FROM nota_fiscal.item WHERE nr_nota = ?";
	//EXCLUIR NOTA NR = DELETE
	private static final String DELETAR_ITEM = "DELETE FROM nota_fiscal.item WHERE id_item = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Item inserir(Item item) {
		
		jdbcTemplate.update(INSERIR_ITEM, item.getDescricao(), item.getValor(), item.getQtd(), item.getNrNota());
		
		return item;
	}
	
	public int alterar(Item item, int id) {
		Object[] params = {item.getDescricao(), item.getValor(), item.getQtd(), item.getNrNota()};
		
		int resultado = jdbcTemplate.update(ALTERAR_ITEM, params, id);
		
		return resultado;
	}
	
	public Item buscarPorId(int id) {
		
		Object[] params = {id};
		
		Item item = jdbcTemplate.queryForObject(BUSCAR_ITEM_ID,
	            BeanPropertyRowMapper.newInstance(Item.class), params);
        
	    return item;
	}
	
	public int excluir(int id) {
		Object[] params = {id};
    	
    	int resultado = jdbcTemplate.update(DELETAR_ITEM, params);
        
        return resultado;
	}
	
	public List<Item> buscarTodos() {
        
        List<Item> listagem = jdbcTemplate.query(BUSCAR_ITEMS,
                    BeanPropertyRowMapper.newInstance(Item.class));
        
        return listagem;
    }
	
	public List<Item> buscarPorNrNota(int nrNota) {
		Object[] params = {nrNota};
		
		List<Item> listaItemsNrNota = jdbcTemplate.query(BUSCAR_NR_NOTA,
                BeanPropertyRowMapper.newInstance(Item.class), params);
		
		return listaItemsNrNota;
	}
	
	public float valorTotal(Item item) {

		float total = (item.getValor() * item.getQtd());
		
		return total;
	}
}
