package org.filho;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.filho.litecommerce.Application;
import org.filho.litecommerce.data.ParametroLojaRepository;
import org.filho.litecommerce.model.ParametroLoja;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class LitecommerceApplicationTests {

  @Autowired private ParametroLojaRepository lojaRepo;
  
  @Test
  public void sempreTemParametro() {
    ParametroLoja parametroUnico = lojaRepo.buscarUnico();
    assertTrue("Parâmetro único da loja não encontrado.", parametroUnico != null);
  }
  
  @Test
  public void parametroTemValorPadrao() {
    // Instancia um novo parametro
    ParametroLoja parametro = new ParametroLoja();
    
    assertTrue(String.format("O valor padrão da despesa do parâmetro da loja difere de 400. [%s]", parametro.getValorTotalDespesas()), parametro.getValorTotalDespesas().compareTo(new BigDecimal(400L)) == 0);
  }

}
