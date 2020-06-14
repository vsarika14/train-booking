package com.sarika.trainbooking.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BerthType {
   LOWER("LOWER"),
   UPPER("UPPER"),
   MIDDLE("MIDDLE"),
   SIDE_LOWER("SIDE_LOWER"),
   SIDE_UPPER("SIDE_UPPER");

   private String value;

   @Override
   public String toString() {
      return value;
   }
}
