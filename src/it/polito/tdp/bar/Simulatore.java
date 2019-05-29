package it.polito.tdp.bar;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.Evento.TipoEvento;

public class Simulatore {
	
	// coda delgi eventi
	private PriorityQueue<Evento> queue = new PriorityQueue<>();

	// modello del mondo
	
	
	// parametri di simulazione
	private LocalTime T_inizio = LocalTime.of(8, 0);
	private int numerotavoli = 15;
	private int tavoli10 = 2;
	private int tavoli8 = 4;
	private int tavoli6 = 4;
	private int tavoli4 = 5;
	private int NEVENTI = 2000;
	private Random rand = new Random();
	
	// statistiche da calcolare
	private int numeroServiti;
	private int numeroAbbandoni;
	private int numeroSoddisfatti;
	private int numeroInsoddisfatti;
	
	// variabili interne
	
	public Simulatore() {
		
	}
	
	public void init() {
		
		LocalTime oraArrivo = T_inizio;
		
		// inizializzo la lista a valori nulli
		this.queue = new PriorityQueue<Evento>();
		
		// aggiungo alla coda gli eventi
		queue.clear();
		for(int i=0; i<NEVENTI; i++) {
			int casuale = rand.nextInt((10-1)+1)+1;
			int numeroCli = rand.nextInt((10-1)+1)+1;
			int durataCas = rand.nextInt((120-60)+1)+60;
			float tolleranza = (float)((Math.random()*10-1)/10);
			
			queue.add(new Evento(oraArrivo, 
					TipoEvento.ARRIVO_GRUPPO_CLIENTI,
					numeroCli,
					tolleranza,
					durataCas));
			
			oraArrivo = oraArrivo.plusMinutes(casuale);
		}
		
		// resetto le statistiche
		this.numeroServiti=0;
		this.numeroAbbandoni=0;
		this.numeroInsoddisfatti=0;
		this.numeroSoddisfatti=0;
	}
	
	public void run() {
		while(!queue.isEmpty()) {
			
			Evento ev = queue.poll();
			if(ev.getTipo()==TipoEvento.ARRIVO_GRUPPO_CLIENTI)
				System.out.println(ev);
			
			switch(ev.getTipo()) {
			
			case LEAVING:
				if(ev.getNumeroClienti()<=4) {
					this.tavoli4++;
				}
				else if(ev.getNumeroClienti()<=6) {
					this.tavoli6++;
				}
				else if(ev.getNumeroClienti()<=8) {
					this.tavoli8++;
				}
				else if(ev.getNumeroClienti()<=10) {
					this.tavoli10++;
				}
				break;
			
			case ARRIVO_GRUPPO_CLIENTI:
				
					if(ev.getNumeroClienti()<=4 && ev.getNumeroClienti()>2) {
						if(this.tavoli4>0) {
							this.tavoli4--;
							this.numeroServiti++;
							this.numeroSoddisfatti+=ev.getNumeroClienti();
							Evento leaving = new Evento(ev.getOraArrivo().plusMinutes(ev.getDurat()),
									TipoEvento.LEAVING,
									ev.getNumeroClienti(),
									-1,
									-1);
							this.queue.add(leaving);
						}
						else {
							float tolleranza2 = (float)((Math.random()*10-1)/10);
							if(ev.getTolleranza()>=tolleranza2) {
								this.numeroServiti++;
								this.numeroSoddisfatti+=ev.getNumeroClienti();
							}
							else {
								this.numeroAbbandoni++;
								this.numeroInsoddisfatti+=ev.getNumeroClienti();
							}
						}
						 
					}
					else if(ev.getNumeroClienti()<=6 && ev.getNumeroClienti()>3) {
						if(this.tavoli6>0) {
							this.tavoli6--;
							this.numeroServiti++;
							this.numeroSoddisfatti+=ev.getNumeroClienti();
							Evento leaving = new Evento(ev.getOraArrivo().plusMinutes(ev.getDurat()),
									TipoEvento.LEAVING,
									ev.getNumeroClienti(),
									-1,
									-1);
							this.queue.add(leaving);
						}
						else {
							float tolleranza2 = (float)((Math.random()*10-1)/10);
							if(ev.getTolleranza()>=tolleranza2) {
								this.numeroServiti++;
								this.numeroSoddisfatti+=ev.getNumeroClienti();
							}
							else {
								this.numeroAbbandoni++;
								this.numeroInsoddisfatti+=ev.getNumeroClienti();
							}
						}
					}
					else if(ev.getNumeroClienti()<=8 && ev.getNumeroClienti()>4) {
						if(this.tavoli8>0) {
							this.tavoli8--;
							this.numeroServiti++;
							this.numeroSoddisfatti+=ev.getNumeroClienti();
							Evento leaving = new Evento(ev.getOraArrivo().plusMinutes(ev.getDurat()),
									TipoEvento.LEAVING,
									ev.getNumeroClienti(),
									-1,
									-1);
							this.queue.add(leaving);
						}
						else {
							float tolleranza2 = (float)((Math.random()*10-1)/10);
							if(ev.getTolleranza()>=tolleranza2) {
								this.numeroServiti++;
								this.numeroSoddisfatti+=ev.getNumeroClienti();
							}
							else {
								this.numeroAbbandoni++;
								this.numeroInsoddisfatti+=ev.getNumeroClienti();
							}
						}
					}
					else if(ev.getNumeroClienti()<=10 && ev.getNumeroClienti()>5) {
						if(this.tavoli10>0) {
							this.tavoli10--;
							this.numeroServiti++;
							this.numeroSoddisfatti+=ev.getNumeroClienti();
							Evento leaving = new Evento(ev.getOraArrivo().plusMinutes(ev.getDurat()),
									TipoEvento.LEAVING,
									ev.getNumeroClienti(),
									-1,
									-1);
							this.queue.add(leaving);
						}
						else {
							float tolleranza2 = (float)((Math.random()*10-1)/10);
							if(ev.getTolleranza()>=tolleranza2) {
								this.numeroServiti++;
								this.numeroSoddisfatti+=ev.getNumeroClienti();
							}
							else {
								this.numeroAbbandoni++;
								this.numeroInsoddisfatti+=ev.getNumeroClienti();
							}
						}
					}
				break;
			}
		}
	}

	public int getNumeroServiti() {
		return numeroServiti;
	}

	public void setNumeroServiti(int numeroServiti) {
		this.numeroServiti = numeroServiti;
	}

	public int getNumeroAbbandoni() {
		return numeroAbbandoni;
	}

	public void setNumeroAbbandoni(int numeroAbbandoni) {
		this.numeroAbbandoni = numeroAbbandoni;
	}

	public int getNumeroSoddisfatti() {
		return numeroSoddisfatti;
	}

	public void setNumeroSoddisfatti(int numeroSoddisfatti) {
		this.numeroSoddisfatti = numeroSoddisfatti;
	}

	public int getNumeroInsoddisfatti() {
		return numeroInsoddisfatti;
	}

	public void setNumeroInsoddisfatti(int numeroInsoddisfatti) {
		this.numeroInsoddisfatti = numeroInsoddisfatti;
	}
	
	
}
