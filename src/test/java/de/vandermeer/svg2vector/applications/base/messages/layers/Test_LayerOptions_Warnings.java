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

package de.vandermeer.svg2vector.applications.base.messages.layers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.vandermeer.execs.options.ExecS_CliParser;
import de.vandermeer.svg2vector.S2VExeception;
import de.vandermeer.svg2vector.applications.base.Test_Artifacts;
import de.vandermeer.svg2vector.applications.base.layers.LayerOptions;

/**
 * Tests for {@link LayerOptions} - warnings.
 *
 * @author     Sven van der Meer &lt;vdmeer.sven@mykolab.com&gt;
 * @version    v2.1.0-SNAPSHOT build 170420 (20-Apr-17) for Java 1.8
 * @since      v2.1.0
 */
public class Test_LayerOptions_Warnings {

	@Test
	public void test_Warning_NoLayers_BN() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"--fout-no-basename",
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertFalse(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("no layers processed but CLI option <fout-no-basename> used, will be ignored", lo.getWarnings().get(0));
	}

	@Test
	public void test_Warning_NoLayers_UseBN() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"--use-basename", "bn"
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertFalse(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("no layers processed but CLI option <use-basename> used, will be ignored", lo.getWarnings().get(0));
	}

	@Test
	public void test_Warning_NoLayers_Index() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"-i"
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertFalse(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("no layers processed but CLI option <fout-index> used, will be ignored", lo.getWarnings().get(0));
	}

	@Test
	public void test_Warning_NoLayers_IsIndex() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"-I"
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertFalse(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("no layers processed but CLI option <fout-isindex> used, will be ignored", lo.getWarnings().get(0));
	}

	@Test
	public void test_Warning_NoLayers_IsLabel() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"-B"
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertFalse(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("no layers processed but CLI option <fout-islabel> used, will be ignored", lo.getWarnings().get(0));
	}

	@Test
	public void test_Warning_Layers_SwitchOn() throws S2VExeception{
		ExecS_CliParser cli = new ExecS_CliParser();
		LayerOptions lo = new LayerOptions();
		cli.addAllOptions(lo.getOptions());
		String[] args = new String[]{
				"-l", "--all-layers", "--fout-index"
		};

		assertEquals(null, cli.parse(args));
		assertEquals(0, Test_Artifacts.setCli4Options(cli.getCommandLine(), lo.getOptions()));

		lo.setOptions(true);
		assertTrue(lo.doLayers());
		assertEquals(1, lo.getWarnings().size());
		assertEquals("layers processed but CLI option <all-layers> used, will be ignored", lo.getWarnings().get(0));
	}
}