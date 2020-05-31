package Cajero;

import java.util.List;

public class ConsultaMov extends MovimientosEnPantalla {
	public ConsultaMov(Cliente usuario,int opcion) {
		super(usuario, opcion);
	}

	public void consultar() {
		todosLosMensajes.tipoDeCuenta();
		int tipo = entrada.nextInt();
		todosLosMensajes.cantidadDeMovimientos(); // pregunta cuantos mov quiere consultar
		int cantidadDeMovimientos = entrada.nextInt();
		switch (tipo) {

		case 1: // ARS
			List<Movimiento> ultimosMovEnARS = usuario.cajaDelClienteARS
			.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
			int length = ultimosMovEnARS.size();
			for (int a = 0; a < length; a++) {
				System.out.println(ultimosMovEnARS.get(a).imprimirMovimiento());
			}
			break;

		case 2: // USD
			List<Movimiento> ultimosMovEnUSD = usuario.cajaDelClienteUSD
			.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
			int lengthUSD = ultimosMovEnUSD.size();
			for (int b = 0; b < lengthUSD; b++) {
				System.out.println(ultimosMovEnUSD.get(b).imprimirMovimiento());
			}
			break;

		case 3: // CC
			List<Movimiento> ultimosMovEnCC = usuario.cuentaCorrienteDelCliente
			.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
			int lengthCC = ultimosMovEnCC.size();
			for (int c = 0; c < lengthCC; c++) {
				System.out.println(ultimosMovEnCC.get(c).imprimirMovimiento());
			}
			break;

		default:
			System.out.println("Valor invalido.");
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;
		}
	}
}

