/*
 * Copyright 2014 Google. Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.k2crypto.storage;

import com.google.k2crypto.K2Exception;

/**
 * Exception thrown when an issue with the storage driver is detected.
 * 
 * @author darylseah@gmail.com (Daryl Seah)
 */
public class StoreDriverException extends K2Exception {
  
  /**
   * Reason why the StoreDriverException was thrown. 
   */
  public static enum Reason {
    /**
     * The driver does not have an accessible no-argument constructor. 
     */
    NO_CONSTRUCTOR("Driver is missing an accessible constructor."),
    
    /**
     * The driver could not be instantiated.
     */
    INSTANTIATE_FAIL("Driver failed to instantiate."),
    
    /**
     * The driver constructor declares throwables that extend classes other
     * than Error or RuntimeException.
     */
    ILLEGAL_THROWS("Driver constructor throws illegal throwables."),
    
    /**
     * The driver is not annotated with {@link StoreDriverInfo}.
     */
    NO_METADATA("Driver is missing meta-data annotation."),
    
    /**
     * The driver identifier declared with {@link StoreDriverInfo} is illegal.
     */
    ILLEGAL_ID("Driver has an illegal identifier.");
    
    final String message;
    
    private Reason(String message) {
      this.message = message;
    }
  }
      
  private final Class<? extends StoreDriver> driverClass;

  private final Reason reason;
  
  /**
   * Constructs a new StoreDriverException.
   *
   * @param driverClass Class of the problematic driver.
   * @param reason The reason the driver is problematic.
   */
  public StoreDriverException(Class<? extends StoreDriver> driverClass,
      Reason reason) {
    super(reason.message);
    this.driverClass = driverClass;
    this.reason = reason;
  }
  
  /**
   * Returns the class of the problematic driver.
   */
  public Class<? extends StoreDriver> getDriverClass() {
    return driverClass;
  }
  
  /**
   * Returns the reason the driver is problematic. 
   */
  public Reason getReason() {
    return reason;
  }
}
