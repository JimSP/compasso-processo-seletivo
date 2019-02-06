package springbootinterview.alexandremoraes.exceptions;

import java.math.BigInteger;

public class ClienteNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -8411966013681879921L;

	private final BigInteger id;

	public ClienteNaoEncontradoException(final BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}
}
