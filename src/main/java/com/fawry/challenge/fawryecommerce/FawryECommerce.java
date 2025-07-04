package com.fawry.challenge.fawryecommerce;

import com.fawry.challenge.fawryecommerce.test.EdgeCaseTester;
import com.fawry.challenge.fawryecommerce.ui.AppUI;

public class FawryECommerce {

    public static void main(String[] args) {
        EdgeCaseTester.runAllTests();
        javax.swing.SwingUtilities.invokeLater(AppUI::new);
    }
}
