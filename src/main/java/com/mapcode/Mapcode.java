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
import java.util.Arrays;

/**
 * This class defines a single Mapcode encoding result, including the Mapcode itself and the
 * territory definition.
 */
public final class Mapcode {
    @Nonnull private final String    mapcode;
    @Nonnull private final String    mapcodeHighPrecision;
    @Nonnull private final String    mapcodeMediumPrecision;
    @Nonnull private final Territory territory;

    public Mapcode(
        @Nonnull final String mapcode,
        @Nonnull final Territory territory) {
        this.mapcodeHighPrecision = mapcode;
        if (mapcode.contains("-")) {
            this.mapcode = mapcode.substring(0, mapcode.length() - 3);
            this.mapcodeMediumPrecision = mapcode.substring(0, mapcode.length() - 1);
        }
        else {
            this.mapcode = mapcode;
            this.mapcodeMediumPrecision = mapcode;
        }
        this.territory = territory;
    }

    /**
     * Get the Mapcode string (without territory information).
     *
     * @return Mapcode string.
     */
    @Nonnull
    public String getMapcode() {
        return mapcode;
    }

    /**
     * Get the high-precision Mapcode string (without territory information).
     * If a high precision code is not available, the regular Mapcode is returned.
     *
     * @return High Precision Mapcode string.
     */
    @Nonnull
    public String getMapcodeHighPrecision() {
        return mapcodeHighPrecision;
    }

    /**
     * Get the medium-precision Mapcode string (without territory information).
     * If a medium precision code is not available, the regular Mapcode is returned.
     *
     * @return Medium Precision Mapcode string.
     */
    @Nonnull
    public String getMapcodeMediumPrecision() {
        return mapcodeMediumPrecision;
    }

    /**
     * Get the territory information.
     *
     * @return Territory information.
     */
    @Nonnull
    public Territory getTerritory() {
        return territory;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{mapcode, territory});
    }

    /**
     * Return the local Mapcode string, potentially ambiguous.
     *
     * Example:
     * 49.4V
     *
     * @return Local Mapcode.
     */
    @Nonnull
    public String asLocal() {
        return mapcode;
    }

    /**
     * Return the full international Mapcode, including the full name of the territory and the Mapcode itself.
     * The format of the code is:
     * full-territory-name mapcode
     *
     * Example:
     * Netherlands 49.4V           (regular code)
     * Netherlands 49.4V-K2        (high precision code)
     *
     * @return Full international Mapcode.
     */
    @Nonnull
    public String asInternationalFullName() {
        return territory.getFullName() + ' ' + mapcode;
    }

    /**
     * Return the international Mapcode as a shorter version using the ISO territory codes where possible.
     * International codes use a territory code "AAA".
     * The format of the code is:
     * short-territory-name mapcode
     *
     * Example:
     * NLD 49.4V                   (regular code)
     * NLD 49.4V-K2                (high-precision code)
     *
     * @return Short-hand international Mapcode.
     */
    @Nonnull
    public String asInternationalISO() {
        return territory.toString() + ' ' + mapcode;
    }

    @Nonnull
    @Override
    public String toString() {
        return asInternationalISO();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Mapcode)) {
            return false;
        }
        final Mapcode that = (Mapcode) obj;
        return mapcode.equals(that.mapcode) && (this.territory.equals(that.territory));
    }
}
