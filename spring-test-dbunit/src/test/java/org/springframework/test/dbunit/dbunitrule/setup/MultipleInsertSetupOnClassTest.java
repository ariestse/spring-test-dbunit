/*
 * Copyright 2010 the original author or authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.test.dbunit.dbunitrule.setup;

import javax.sql.DataSource;

import org.github.philwebb.springtestdbunit.DbUnitRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.dbunit.annotation.DatabaseOperation;
import org.springframework.test.dbunit.annotation.DatabaseSetup;
import org.springframework.test.dbunit.entity.EntityAssert;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/dbunit-context.xml")
@DatabaseSetup(type = DatabaseOperation.CLEAN_INSERT, value = { "/META-INF/db/insert.xml", "/META-INF/db/insert2.xml" })
@Transactional
public class MultipleInsertSetupOnClassTest {

	@Rule
	public DbUnitRule dbUnit = new DbUnitRule();

	@Autowired
	DataSource dataSource;

	@Autowired
	private EntityAssert entityAssert;

	@Test
	public void test() throws Exception {
		this.entityAssert.assertValues("fromDbUnit", "fromDbUnit2");
	}
}
