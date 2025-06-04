Testfall-Nr.	      Beschreibung	                                                                         Fahrradtyp   	Offene Aufträge (gesamt)	  Kunde hat schon Auftrag?	   Erwartung
1	            Gültiger Auftrag, alles erlaubt	                                                               RACE	              0                          	Nein	                     true
2	             Fahrradtyp ist GRAVEL – nicht erlaubt	                                                        GRAVEL	            0	                          Nein	                    false
3	             Fahrradtyp ist EBIKE – nicht erlaubt                                                          	EBIKE              	0	                          Nein                    	false
4              	Kunde hat bereits einen offenen Auftrag                                                      	FIXIE	              1                          	Ja                      	false
5              	4 offene Aufträge vorhanden – Grenzwert gültig	                                              SINGLE_SPEED	      4	                          Nein	                     true
6              	5 offene Aufträge vorhanden – über Grenzwert 	                                                FIXIE              	5                          	Nein	                     false
7	              Verbotenes Fahrrad & Kunde hat offenen Auftrag & zu viele Aufträge (Kombi-Test)	              GRAVEL	            5                          	Ja	                       false
