/************************************************************************************
 * The MIT License                                                                  *
 *                                                                                  *
 * Copyright (c) 2013 Marcelo Guimarães <ataxexe at gmail dot com>                  *
 * -------------------------------------------------------------------------------- *
 * Permission  is hereby granted, free of charge, to any person obtaining a copy of *
 * this  software  and  associated documentation files (the "Software"), to deal in *
 * the  Software  without  restriction,  including without limitation the rights to *
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of *
 * the  Software, and to permit persons to whom the Software is furnished to do so, *
 * subject to the following conditions:                                             *
 *                                                                                  *
 * The  above  copyright notice and this permission notice shall be included in all *
 * copies or substantial portions of the Software.                                  *
 *                            --------------------------                            *
 * THE  SOFTWARE  IS  PROVIDED  "AS  IS",  WITHOUT WARRANTY OF ANY KIND, EXPRESS OR *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS *
 * FOR  A  PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR *
 * COPYRIGHT  HOLDERS  BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER *
 * IN  AN  ACTION  OF  CONTRACT,  TORT  OR  OTHERWISE,  ARISING  FROM, OUT OF OR IN *
 * CONNECTION  WITH  THE  SOFTWARE  OR  THE  USE OR OTHER DEALINGS IN THE SOFTWARE. *
 ************************************************************************************/

package br.pjsign.robomath;

import br.pjsign.robomath.utils.Utils;

/** @author Marcelo Guimarães */
public class Angle {

  public static final Angle ZERO   = new Angle(0);
  public static final Angle PI     = new Angle((float) Math.PI);
  public static final Angle TWO_PI = new Angle((float) Math.PI * 2);

  public static final Angle PI_OVER_TWO       = new Angle((float) Math.PI / 2);
  public static final Angle MINUS_PI_OVER_TWO = new Angle((float) -Math.PI / 2);
 
  public static final Angle PI_OVER_FOUR       = new Angle((float) Math.PI / 4);
  public static final Angle MINUS_PI_OVER_FOUR = new Angle((float) -Math.PI / 4);

  private final float radians;

  public Angle(float radians) {
    this.radians = radians;
  }

  public float radians() {
    return radians;
  }

  public float degrees() {
    return (float) Math.toDegrees(radians);
  }

  public Angle plus(Angle angle) {
    return new Angle(radians + angle.radians);
  }

  public Angle plus(float angle) {
    return plus(new Angle(angle));
  }

  public Angle minus(Angle angle) {
    return new Angle(radians - angle.radians);
  }

  public Angle minus(float angle) {
    return minus(new Angle(angle));
  }

  public Angle inverse() {
    return new Angle(-radians);
  }

  public static float cos(Angle angle) {
    return (float) Math.cos(angle.radians());
  }

  public static float sin(Angle angle) {
    return (float) Math.sin(angle.radians());
  }

  public static float tan(Angle angle) {
    return (float) Math.tan(angle.radians());
  }

  public Angle toRight() {
    return new Angle(Math.abs(radians));
  }

  public Angle toLeft() {
    return new Angle(-Math.abs(radians));
  }
  
  public Angle relative() {
    return new Angle((float) Utils.normalRelativeAngle(radians));
  }

  public Angle absolute() {
    return new Angle((float) Utils.normalAbsoluteAngle(radians));
  }

  @Override
  public String toString() {
    return "Angle{" +
            "radians=" + radians +
            ", degreeus=" + degrees() +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Angle angle = (Angle) o;

    return Utils.areEquals(angle.radians(), radians());
  }

  @Override
  public int hashCode() {
    return Utils.toBigecimal(radians).hashCode();
  }

  public static Angle inDegrees(float degrees) {
    return new Angle((float) Math.toRadians(degrees));
  }

  public static Angle inRadians(float radians) {
    return new Angle(radians);
  }

}
