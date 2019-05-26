package br.com.odontoCorretorProposta.convert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.odontoCorretorProposta.entity.Proposta;

@Service
public class CalendarToString {

	public List<Proposta> converteLista(List<Proposta> listaPropostas, String formato) {
		DateFormat df = new SimpleDateFormat(formato);
		for (Proposta proposta : listaPropostas) {
			proposta.setDataAssinaturaString(df.format(proposta.getDataAssinatura().getTime()));
		 }
		return listaPropostas;
	}
	
	public String converteData(Calendar calendar, String formato) {
		DateFormat df = new SimpleDateFormat(formato);
		return df.format(calendar.getTime());
	}

}
