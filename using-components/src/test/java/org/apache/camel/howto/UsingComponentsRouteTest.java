/*
 * Copyright (C) Bilgin Ibryam http://www.ofbizian.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.howto;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

import static org.apache.camel.builder.Builder.constant;
import static org.hamcrest.CoreMatchers.is;

public class UsingComponentsRouteTest extends CamelTestSupport {
    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new UsingComponentsRoute();
    }

    @EndpointInject(uri = "mock://http:www.google.co.uk")
    private MockEndpoint mockResult;

    @Produce(uri = "direct:start")
    protected ProducerTemplate start;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:start");
                mockEndpointsAndSkip("*");
            }
        });

        mockResult.returnReplyBody(constant("Result From Target Endpoint"));
    }

    @Test
    public void returnsResultFromTargetEndpoint() throws Exception {

        String result = start.requestBody((Object) "Some query", String.class);
        assertThat(result, is("Result From Target Endpoint"));
    }
}
