/* Copyright 2017 Sven van der Meer <vdmeer.sven@mykolab.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.vandermeer.svg2vector.applications.base.layers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.vandermeer.execs.DefaultCliParser;
import de.vandermeer.skb.interfaces.application.ApplicationException;
import de.vandermeer.skb.interfaces.application.CliParseException;
import de.vandermeer.svg2vector.applications.core.ErrorCodes;

/**
 * Tests for {@link LayerOptions}.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v2.1.0-SNAPSHOT build 170420 (20-Apr-17) for Java 1.8
 * @since      v2.1.0
 */
public class Test_LayerOptions {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void test_Constructor_Values(){
		LayerOptions lo = new LayerOptions();
		assertEquals(7, lo.getSimpleOptions().size());
		assertEquals(1, lo.getTypedOptions().size());
		assertFalse(lo.doLayers());
		assertFalse(lo.allLayersOn());
		assertEquals(0, lo.getWarnings().size());
	}

	@Test
	public void test_NothingSet() throws ApplicationException{
		LayerOptions lo = new LayerOptions();
		lo.setOptions(true);

		lo = new LayerOptions();
		lo.setOptions(false);
		assertFalse(lo.doLayers());
	}

	@Test
	public void test_LayerTrue() throws ApplicationException, IllegalStateException, CliParseException{
		DefaultCliParser cli = new DefaultCliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getAllOptions());

		String[] args = new String[]{
				"-l", "-i"
		};
		cli.parse(args);

		lo.setOptions(true);
		assertTrue(lo.doLayers());
	}

	@Test
	public void test_Error_LayerFalse() throws ApplicationException, IllegalStateException, CliParseException{
		DefaultCliParser cli = new DefaultCliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getAllOptions());

		String[] args = new String[]{
				"-l"
		};
		cli.parse(args);

		thrown.expect(ApplicationException.class);
		thrown.expectMessage("layers requested in command line, but SVG input file has no layers");
		thrown.expect(hasProperty("errorCode", is(ErrorCodes.LAYERS_REQUESTED_DOC_WITHOUT_LAYERS__0.getCode())));
		lo.setOptions(false);
	}

	@Test
	public void test_IfexistsTrue() throws ApplicationException, IllegalStateException, CliParseException{
		DefaultCliParser cli = new DefaultCliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getAllOptions());

		String[] args = new String[]{
				"-L", "-i"
		};
		cli.parse(args);

		lo.setOptions(true);
		assertTrue(lo.doLayers());
	}

	@Test
	public void test_IfexistsFalse() throws ApplicationException, IllegalStateException, CliParseException{
		DefaultCliParser cli = new DefaultCliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getAllOptions());

		String[] args = new String[]{
				"-L",
		};
		cli.parse(args);

		lo.setOptions(false);
		assertFalse(lo.doLayers());
	}

}
