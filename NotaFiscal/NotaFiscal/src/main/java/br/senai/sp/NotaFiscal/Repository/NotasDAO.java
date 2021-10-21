package br.senai.sp.NotaFiscal.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.senai.sp.NotaFiscal.Model.Item;
import br.senai.sp.NotaFiscal.Model.NotaFiscal;

@Repository
public class NotasDAO {
	
	private static final String INSERIR_NOTA = "INSERT INTO nota_fiscal.nota(cliente, dt_emissao) VALUES (?,?)";
	private static final String ALTERAR_NOTA = "UPDATE nota_fiscal.nota SET cliente = ?, dt_emissao = ? WHERE nr_nota = ?";
	private static final String BUSCAR_NOTAS = "SELECT nr_nota, cliente, dt_emissao FROM nota_fiscal.nota";
	private static final String BUSCAR_NOTA_ID = "SELECT nr_nota, cliente, dt_emissao FROM nota_fiscal.nota WHERE nr_nota = ?";
    private static final String DELETAR_NOTA = "DELETE FROM nota_fiscal.nota WHERE nr_nota =?";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    ItemsDAO itemsDao;
    
    public NotaFiscal inserir(NotaFiscal nota) {
    	
    	
    	KeyHolder holder = new GeneratedKeyHolder();
    	jdbcTemplate.update(new PreparedStatementCreator() {           

            	@Override
            	public PreparedStatement createPreparedStatement(Connection connection)throws SQLException {
            		
                PreparedStatement ps = connection.prepareStatement(INSERIR_NOTA,
                Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nota.getCliente());
                ps.setDate(2, new Date(nota.getData_emissao().getYear(),
                		nota.getData_emissao().getMonthValue(), nota.getData_emissao().getDayOfMonth()));
                return ps;
            }
        }, holder);
    	
    	for (Item items : nota.getItems()) {
    		items.setNrNota(holder.getKey().intValue());
			itemsDao.inserir(items);
		}
    	
    	return nota;
    }
    
    public int alterar(NotaFiscal nota, int id) {
    	Object[] params = {nota.getCliente(), nota.getData_emissao(), nota.getNr_nota()};
    	
    	int resultado =  jdbcTemplate.update(ALTERAR_NOTA, params);
    	
    	for (Item items : nota.getItems()) {
    		//itemsDao.deletar
    		//itemsDao.inserir
			itemsDao.excluir(items.getId_item());
			itemsDao.inserir(items);
		}
    	
        return resultado;
    }
    
    public NotaFiscal buscarPorId(int id) {
    	
    	Object[] params = {id};
    	
        NotaFiscal nota = jdbcTemplate.queryForObject(BUSCAR_NOTA_ID,
            BeanPropertyRowMapper.newInstance(NotaFiscal.class), params);
        	
			nota.setItems(itemsDao.buscarPorNrNota(nota.getNr_nota()));
             
        return nota;
    }
    
    public int excluir(int id) {
    	Object[] params = {id};
    	
    	int resultado = jdbcTemplate.update(DELETAR_NOTA, params);
    	
        return resultado;
    }
    
    public List<NotaFiscal> buscarTodos() {
        
        List<NotaFiscal> listagem = jdbcTemplate.query(BUSCAR_NOTAS,
                    BeanPropertyRowMapper.newInstance(NotaFiscal.class));
        
        for (NotaFiscal notaFiscal : listagem) {
        	
			//itemsDao.buscarPorNota vai devolver um list de items.
        	
			notaFiscal.setItems(itemsDao.buscarPorNrNota(notaFiscal.getNr_nota()));
        	
			//notaFiscal.setItems(itemsDao.buscarporNrNota(int nrNota));
		}
        
        return listagem;
    }
	
}
