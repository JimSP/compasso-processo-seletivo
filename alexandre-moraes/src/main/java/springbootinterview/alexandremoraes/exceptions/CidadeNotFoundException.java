package springbootinterview.alexandremoraes.exceptions;

import java.math.BigInteger;

public class CidadeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2946021211668138325L;

	private final BigInteger id;

	public CidadeNotFoundException(final BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}
}
