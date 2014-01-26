package com.github.hcguersoy.redisexample;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A object to store measure data.
 *
 * @author hcguersoy
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MeasureData implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The Data KEY of the Object.
	 */
	public final static String KEY = "MEASURE";

	/**
	 * This field will be used as the redis hashkey of the data object. 
	 * This has to be unique.
	 */
	@NonNull
	private String measureId;
	
	@NonNull
	private String gauge;
	
	@NonNull
	private BigInteger measurementData;

	@NonNull
	private Date measureDate;
	
}
