package it.biglietti;  
// gruppo 4 

import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {
	// Costanti
	private final BigDecimal costoKm = new BigDecimal("0.21");
	private final BigDecimal scontoOver = new BigDecimal("0.6");
	private final BigDecimal scontoUnder = new BigDecimal("0.8");
	private final int durataNormale = 30;
	private final int durataFlessibile = 90;

	// attributi
	private int km;
	private int eta;
	private LocalDate data;
	private boolean flessibile;

	// Costruttore
	public Biglietto(int km, int eta, boolean flessibile) throws Exception {
		if (!isValidKm(km)) {
			throw new Exception("km non valido.");
		}
		if (!isValidEta(eta)) {
			throw new Exception("eta non valida");
		}
		this.km = km;
		this.eta = eta;
		this.flessibile = flessibile;
		this.data = null;
	}

	// Metodi
	boolean isValidKm(int km) {
		boolean valid = false;
		if (km > 0) {
			valid = true;
		}
		return valid;
	}

	boolean isValidEta(int eta) {
		boolean valid = false;
		if (eta >= 0 && eta < 120) {
			valid = true;
		}
		return valid;
	}

	private BigDecimal calcolaSconto() {
		if (eta > 65) {
			return scontoOver;
		} else if (eta < 18) {
			return scontoUnder;
		} else {
			return new BigDecimal("1");
		}
	}

	public BigDecimal calcolaPrezzo() {
		BigDecimal x = new BigDecimal(km);
		BigDecimal y = new BigDecimal(1.1);
		x = x.multiply(costoKm);
		if (flessibile == false) {
			return x.multiply(calcolaSconto());
		} else {
			return x.multiply(calcolaSconto()).multiply(y);
		}
	}

	public LocalDate calcolaDataScadenza() {
		if (flessibile == false) {
			return data.plusDays(durataNormale);
		} else {
			return data.plusDays(durataFlessibile);
		}

	}

}
