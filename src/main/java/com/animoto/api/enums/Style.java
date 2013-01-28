package com.animoto.api.enums;

public enum Style {
	ORIGINAL("original"),
	MOTHERS_DAY_2011("mothers_day_2011"),
	ELEGANCE("elegance_12"),
	VINTAGE("vintage"),
	COSMIC_TIDINGS("cosmic_tidings"),
	WONDERLAND_OF_SNOW("wonderland_of_snow"),
	THROUGH_THE_BLOSSOMS("valentines_day_2012_hands"),
	WATERCOLOR_SEASHORE("mothers_day_2011"),
	WATER("elements_water"),
	SIMPLICITY("slideshow_1"),
	AIR("elements_air"),
	FIRE("elements_fire"),
	EARTH("elements_earth"),
	COLOR_FOLD("Cubist_style"),
	COMING_UP_ROSES("valentines_2011"),
	WRAPPING_SCRAPS("xmas-2010-style1"),
	THE_WINDING_VINE("mothers_day"),
	POPUP_PANDEMONIUM("holiday_popup_pandemonium"),
	STARRY_NIGHT("holiday_starry_night"),
	RETRO_WHEEL("viewmaster"),
	AUTUMN("autumn"),
	PARTY("party"),
	VINTAGE_INK("vintage_ink"),
	FIREWORKS("fireworks"),
	BRILLIANCE("elegant"),
	EVENTS("events"),
	LAND_OF_THE_RISING_HEARTS("land_of_the_rising_hearts");

  private String value;

  Style(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
