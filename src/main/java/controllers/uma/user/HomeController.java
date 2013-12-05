/**
 * Copyright (C) 2013 the original author or authors.
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

package controllers.uma.user;

import models.uma.User;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import uma.annotations.LoggedUser;

import com.google.inject.Singleton;

import filters.uma.auth.SecurityFilter;

@Singleton
public class HomeController {
    public static enum Method{
        index
    }

    @FilterWith(SecurityFilter.class)
    public Result index(@LoggedUser User user){
        return Results.html().render("userAuth", user);
    }
}
