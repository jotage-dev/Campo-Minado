package br.com.cod3r.cm.visao;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.cod3r.cm.modelo.Campo;
import br.com.cod3r.cm.modelo.CampoEvento;
import br.com.cod3r.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener {

	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color TEXTO_VERDE = new Color(0, 100, 0);

	private Campo campo;

	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBackground(BG_PADRAO);
		setOpaque(true);
		
		setBorder(BorderFactory.createBevelBorder(0));
		addMouseListener(this);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}

	}

	private void aplicarEstiloPadrao() {
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
setText("");
	}

	private void aplicarEstiloExplodir() {
		setForeground(Color.WHITE);
		setBackground(BG_EXPLODIR);
		setText("X");
		
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCADO);
		setForeground(Color.black);
		setText("M");

	}

	private void aplicarEstiloAbrir() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		setBackground(BG_PADRAO);

		switch (campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);

		}
String valor = !campo.vizinhancaSegura() ? 
		campo.minasNaVizinhanca() + "":"";
setText(valor);

	
	
	}

	// Interface dos eventos do mouse
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		if (e.getButton() == 1) {
			campo.abrir();
		} else {
			campo.alternaMarcacao();
		}

	}

	public void mouseClicked(java.awt.event.MouseEvent e) {
	}

	public void mouseEntered(java.awt.event.MouseEvent e) {
	}

	public void mouseExited(java.awt.event.MouseEvent e) {
	}

	public void mouseReleased(java.awt.event.MouseEvent e) {
	}

}
