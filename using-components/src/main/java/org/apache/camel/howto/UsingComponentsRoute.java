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

import org.apache.camel.builder.RouteBuilder;

public class UsingComponentsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jetty:http://localhost:8181?matchOnUriPrefix=true")
                .to("log:org.apache.camel.howto?showAll=true&multiline=true")
                .to("jetty:http://www.google.co.uk?bridgeEndpoint=true&throwExceptionOnFailure=false");
    }

}

