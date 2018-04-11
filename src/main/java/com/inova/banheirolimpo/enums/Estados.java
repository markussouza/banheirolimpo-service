/**
 * 
 */
package com.inova.banheirolimpo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Markus Souza
 *
 */

@ToString
@AllArgsConstructor
public enum Estados {
	
	AC("Acre"),  
    AL("Alagoas"),  
    AM("Amazonas"),  
    AP("Amapá"),  
    BA("Bahia"),  
    CE("Ceará"),  
    DF("Distrito Federal"),  
    ES("Espirito Santo"),  
    GO("Goiás"),  
    MA("Maranhão"),  
    MG("Minas Gerais"),  
    MS("Mato Grosso Sul"),  
    MT("Mato Grosso"),  
    PA("Pará"),  
    PB("Paraiba"),  
    PE("Pernanbuco"),  
    PI("Piaui"),  
    PR("Parana"),  
    RJ("Rio de Janeiro"),  
    RN("Rio Grande do Norte"),  
    RO("Rondônia"),  
    RR("Roraima"),  
    RS("Rio Grande do Sul"),  
    SC("Santa Catarina"),  
    SE("Sergipe"),  
    SP("São Paulo"),  
    TO("Tocantins");  
    
	@Getter
	private String estado;

}
