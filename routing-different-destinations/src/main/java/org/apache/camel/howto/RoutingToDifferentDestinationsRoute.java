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

public class RoutingToDifferentDestinationsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://source")
            .choice()
                .when(simple("${in.header.CamelFileName} contains 'widget.txt'"))
                    .to("file://widget")
                .when(simple("${in.header.CamelFileName} contains 'gadget.txt'"))
                    .to("file://gadget")
                .otherwise()
                    .to("log://org.apache.camel.howto");
    }
}
