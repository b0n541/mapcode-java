/*
 * Copyright (C) 2014 Stichting Mapcode Foundation (http://www.mapcode.com)
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

package com.mapcode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

class CheckArgs {

    /**
     * Package private helper methods.
     */

    static void checkRange(@Nonnull final String param, final double value,
        final double min, final double max) throws IllegalArgumentException {
        if ((value < min) || (value > max)) {
            throw new IllegalArgumentException("Parameter " + param +
                " should be in range [" + min + ", " + max + "], but is: " + value);
        }
    }

    static void checkNonnull(@Nonnull final String param, @Nullable final Object obj)
        throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Parameter " + param + " should not be null");
        }
    }

}
