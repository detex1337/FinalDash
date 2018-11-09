package com.dtx.finaldash;

import java.io.Serializable;

<<<<<<< HEAD
public class Puntuacion implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id, puntos;

	public Puntuacion(int id, int puntos) {
		super();
		this.id = id;
		this.puntos = puntos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
=======
public class Puntuacion implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id, puntos;

    public Puntuacion(int id, int puntos) {
        super();
        this.id = id;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


>>>>>>> Arreglos de codigo y optimizacion
}
