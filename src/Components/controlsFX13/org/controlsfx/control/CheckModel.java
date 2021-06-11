/**
 * Copyright (c) 2014, 2018 ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package Components.controlsFX13.org.controlsfx.control;

import javafx.collections.ObservableList;

public interface CheckModel<T> {
    
    /**
     * Returns the count of items in the control.
     */
    public int getItemCount();

    /**
     * Returns a read-only list of the currently checked items in the control.
     */
    public ObservableList<T> getCheckedItems();

    /**
     * Checks all items in the control
     */
    public void checkAll();

    /**
     * Unchecks the given item in the control
     * @param item The item to uncheck.
     */
    public void clearCheck(T item);
    
    /**
     * Unchecks all items in the control
     */
    public void clearChecks();
    
    /**
     * Returns true if there are no checked items in the control.
     */
    public boolean isEmpty();

    /**
     * Returns true if the given item is checked in the control.
     * @param item Item whose check property is to be tested.
     */
    public boolean isChecked(T item);
    
    /**
     * Checks the given item in the control.
     * @param item The item to check.
     */
    public void check(T item);

    /**
     * Toggles the check state for the given item in the control.
     * @param item The item for which check state needs to be toggled.
     */
    public void toggleCheckState(T item);
}
