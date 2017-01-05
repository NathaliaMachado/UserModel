package aula.ps.test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import aula.ps.dao.PessoaDao;
import aula.ps.user.Pessoa;


public class CrudTest {

	public static void main(String[] args) {
		
   try {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Lucy");
		pessoa.setEmail("bigboss@gmail.com");
		pessoa.setCpf("3553553535");
		pessoa.setTelefone("95959-9595");
		pessoa.setSexo('F');
		pessoa.setDatanascimento(new Date (1980/8/19));
		
		PessoaDao dao = new PessoaDao();

		dao.create(pessoa);
		
		pessoa.setNome("Marie");
		
	    dao.update(pessoa);
	    
	    List<Pessoa> pessoas = dao.getList();
	    for (Pessoa pesssoa : pessoas) {
	    	
	    	System.out.println("Nome: " + pesssoa.getNome());
	    	System.out.println("Email: " + pesssoa.getEmail());		
			System.out.println("CPF: " + pesssoa.getCpf());
			System.out.println("Telefone: " + pesssoa.getTelefone());
			System.out.println("Sexo: " + pesssoa.getSexo());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			System.out.println("Data de nascimento: " + sdf.format(pesssoa.getDatanascimento().getTime()));
			System.out.println(" ");
	    }
	    
		dao.delete(pessoa);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
