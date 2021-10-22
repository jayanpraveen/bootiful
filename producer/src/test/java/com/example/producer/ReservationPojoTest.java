package com.example.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReservationPojoTest {

	@Test
	public void create() throws Exception {

		Reservation re = new Reservation("1", "tommy");

		assertEquals(re.getName(), "tommy");
		assertNotNull(re.getId());
		assertThat(re.getName(), Matchers.equalToIgnoringCase("ToMmy"));

	}

}
