package Cajero;
import java.io.*;



public class ArchivoDeCuentas {
	
	private ArchivoDeClientes archivoCliente;
	private ArchivoDeTarjetas archivoTarjetas;


	public ArchivoDeCuentas() throws FileNotFoundException, IOException {
		this.archivoCliente= new ArchivoDeClientes();		//Creo dos objetos, de archivoCliente y de archivoTarjeta
		this.archivoTarjetas=new ArchivoDeTarjetas();		//Se crean esos dos objetos para tener dos mapas. Uno: CUIT | CLIENTE y otro ALIAS | CUIT
		try {
			crearCuenta();
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
	}
	
	private void crearCuenta() throws Exception {

		BufferedReader lecturaArchivoCuentas = new BufferedReader(new FileReader("ArchivoCuentas.txt"));

		String lineaArchivoCuentas = lecturaArchivoCuentas.readLine();

		while ((lineaArchivoCuentas != null)) {

			lineaArchivoCuentas.trim();

			String separadorArchivoCuentas[] = lineaArchivoCuentas.split(",");
//////////////////////////////////////EXTRAER VALORES///////////////////////////////////////////////////
			
			int tipoDeCuenta = Integer.parseInt(separadorArchivoCuentas[0]);

			String alias = separadorArchivoCuentas[1];

			double saldo = Double.parseDouble(separadorArchivoCuentas[2]);
			
			double descubierto=0;
			if(tipoDeCuenta==02) {
				descubierto=Double.parseDouble(separadorArchivoCuentas[3]);
			}
			
//////////////////////////////////ASOCIAR CUENTA A CLIENTES////////////////////////////////////////////////////////	
			long cuitMOD=archivoCliente.getAliasConCuit().get(alias);
			switch (tipoDeCuenta) {
			case 01: 
				Cliente clienteARS = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteARS.asociarAhorroARS(saldo, alias);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteARS);
				break;
			case 02: 
				Cliente clienteCorriente = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteCorriente.asociarCorriente(saldo, alias, descubierto);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteCorriente);
				break;
			case 03: 
				Cliente clienteUSD = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteUSD.asociarAhorroUSD(saldo, alias);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteUSD);
				break;
			}

			lineaArchivoCuentas = lecturaArchivoCuentas.readLine();
		}
		lecturaArchivoCuentas.close();
	}
	public ArchivoDeClientes getArchivoCliente() {
		return archivoCliente;
	}

	public ArchivoDeTarjetas getArchivoTarjetas() {
		return archivoTarjetas;
	}
	public Cuenta encontrarCuentaPorAlias(String aliasDestinatario) {
		if (archivoCliente.getAliasConCuit().containsKey(aliasDestinatario)) { 
			long cuitDestinatario = archivoCliente.getAliasConCuit()
					.get(aliasDestinatario);
			Cliente clienteDestinatario = archivoTarjetas.getClientesConCuit()
					.get(cuitDestinatario);
			if (clienteDestinatario.cajaDelClienteARS.alias.equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cajaDelClienteARS;
				
			} else if (clienteDestinatario.cajaDelClienteUSD.alias.equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cajaDelClienteUSD;
				
			} else if (clienteDestinatario.cuentaCorrienteDelCliente.alias.equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cuentaCorrienteDelCliente;
			} else {
				return null;
			}
			
	} else {
		return null;
		}
	}
}