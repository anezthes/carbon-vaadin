import 'construct-style-sheets-polyfill';
import { unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin/register-styles';

const createLinkReferences = (css, target) => {
  // Unresolved urls are written as '@import url(text);' to the css
  // [0] is the full match
  // [1] matches the media query
  // [2] matches the url
  const importMatcher = /(?:@media\s(.+?))?(?:\s{)?\@import\surl\((.+?)\);(?:})?/g;
  
  var match;
  var styleCss = css;
  
  // For each external url import add a link reference
  while((match = importMatcher.exec(css)) !== null) {
    styleCss = styleCss.replace(match[0], "");
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = match[2];
    if (match[1]) {
      link.media = match[1];
    }
    // For target document append to head else append to target
    if (target === document) {
      document.head.appendChild(link);
    } else {
      target.appendChild(link);
    }
  };
  return styleCss;
};

// target: Document | ShadowRoot
export const injectGlobalCss = (css, target, first) => {
  if(target === document) {
    const hash = getHash(css);
    if (window.Vaadin.theme.injectedGlobalCss.indexOf(hash) !== -1) {
      return;
    }
    window.Vaadin.theme.injectedGlobalCss.push(hash);
  }
  const sheet = new CSSStyleSheet();
  sheet.replaceSync(createLinkReferences(css,target));
  if (first) {
    target.adoptedStyleSheets = [sheet, ...target.adoptedStyleSheets];
  } else {
    target.adoptedStyleSheets = [...target.adoptedStyleSheets, sheet];
  }
};
import stylesCss from 'themes/carbon/styles.css?inline';
import { typography } from '@vaadin/vaadin-lumo-styles/typography.js';
import { color } from '@vaadin/vaadin-lumo-styles/color.js';
import { spacing } from '@vaadin/vaadin-lumo-styles/spacing.js';
import { badge } from '@vaadin/vaadin-lumo-styles/badge.js';
import { utility } from '@vaadin/vaadin-lumo-styles/utility.js';
import inputButtonCss from 'themes/carbon/components/input-button.css?inline';
import inputFieldCss from 'themes/carbon/components/input-field.css?inline';
import vaadinAccordionPanelCss from 'themes/carbon/components/vaadin-accordion-panel.css?inline';
import vaadinAppLayoutCss from 'themes/carbon/components/vaadin-app-layout.css?inline';
import vaadinButtonCss from 'themes/carbon/components/vaadin-button.css?inline';
import vaadinCheckboxGroupCss from 'themes/carbon/components/vaadin-checkbox-group.css?inline';
import vaadinCheckboxCss from 'themes/carbon/components/vaadin-checkbox.css?inline';
import vaadinComboBoxItemCss from 'themes/carbon/components/vaadin-combo-box-item.css?inline';
import vaadinComboBoxOverlayCss from 'themes/carbon/components/vaadin-combo-box-overlay.css?inline';
import vaadinComboBoxScrollerCss from 'themes/carbon/components/vaadin-combo-box-scroller.css?inline';
import vaadinComboBoxCss from 'themes/carbon/components/vaadin-combo-box.css?inline';
import vaadinContextMenuItemCss from 'themes/carbon/components/vaadin-context-menu-item.css?inline';
import vaadinContextMenuListBoxCss from 'themes/carbon/components/vaadin-context-menu-list-box.css?inline';
import vaadinContextMenuOverlayCss from 'themes/carbon/components/vaadin-context-menu-overlay.css?inline';
import vaadinDatePickerOverlayContentCss from 'themes/carbon/components/vaadin-date-picker-overlay-content.css?inline';
import vaadinDatePickerTextFieldCss from 'themes/carbon/components/vaadin-date-picker-text-field.css?inline';
import vaadinDatePickerCss from 'themes/carbon/components/vaadin-date-picker.css?inline';
import vaadinDialogOverlayCss from 'themes/carbon/components/vaadin-dialog-overlay.css?inline';
import vaadinDialogCss from 'themes/carbon/components/vaadin-dialog.css?inline';
import vaadinDrawerToggleCss from 'themes/carbon/components/vaadin-drawer-toggle.css?inline';
import vaadinGridSorterCss from 'themes/carbon/components/vaadin-grid-sorter.css?inline';
import vaadinGridCss from 'themes/carbon/components/vaadin-grid.css?inline';
import vaadinInputContainerCss from 'themes/carbon/components/vaadin-input-container.css?inline';
import vaadinItemCss from 'themes/carbon/components/vaadin-item.css?inline';
import vaadinListBoxCss from 'themes/carbon/components/vaadin-list-box.css?inline';
import vaadinMenuBarButtonCss from 'themes/carbon/components/vaadin-menu-bar-button.css?inline';
import vaadinMenuBarCss from 'themes/carbon/components/vaadin-menu-bar.css?inline';
import vaadinMonthCalendarCss from 'themes/carbon/components/vaadin-month-calendar.css?inline';
import vaadinNotificationCardCss from 'themes/carbon/components/vaadin-notification-card.css?inline';
import vaadinNumberFieldCss from 'themes/carbon/components/vaadin-number-field.css?inline';
import vaadinPasswordFieldButtonCss from 'themes/carbon/components/vaadin-password-field-button.css?inline';
import vaadinPasswordFieldCss from 'themes/carbon/components/vaadin-password-field.css?inline';
import vaadinProgressBarCss from 'themes/carbon/components/vaadin-progress-bar.css?inline';
import vaadinRadioButtonCss from 'themes/carbon/components/vaadin-radio-button.css?inline';
import vaadinRadioGroupCss from 'themes/carbon/components/vaadin-radio-group.css?inline';
import vaadinSelectItemCss from 'themes/carbon/components/vaadin-select-item.css?inline';
import vaadinSelectOverlayCss from 'themes/carbon/components/vaadin-select-overlay.css?inline';
import vaadinSelectValueButtonCss from 'themes/carbon/components/vaadin-select-value-button.css?inline';
import vaadinSelectCss from 'themes/carbon/components/vaadin-select.css?inline';
import vaadinTabCss from 'themes/carbon/components/vaadin-tab.css?inline';
import vaadinTabsCss from 'themes/carbon/components/vaadin-tabs.css?inline';
import vaadinTextAreaCss from 'themes/carbon/components/vaadin-text-area.css?inline';
import vaadinTextFieldCss from 'themes/carbon/components/vaadin-text-field.css?inline';
import vaadinUploadFileCss from 'themes/carbon/components/vaadin-upload-file.css?inline';
import vaadinUploadCss from 'themes/carbon/components/vaadin-upload.css?inline';

window.Vaadin = window.Vaadin || {};
window.Vaadin.theme = window.Vaadin.theme || {};
window.Vaadin.theme.injectedGlobalCss = [];

/**
 * Calculate a 32 bit FNV-1a hash
 * Found here: https://gist.github.com/vaiorabbit/5657561
 * Ref.: http://isthe.com/chongo/tech/comp/fnv/
 *
 * @param {string} str the input value
 * @returns {string} 32 bit (as 8 byte hex string)
 */
function hashFnv32a(str) {
  /*jshint bitwise:false */
  let i, l, hval = 0x811c9dc5;

  for (i = 0, l = str.length; i < l; i++) {
    hval ^= str.charCodeAt(i);
    hval += (hval << 1) + (hval << 4) + (hval << 7) + (hval << 8) + (hval << 24);
  }

  // Convert to 8 digit hex string
  return ("0000000" + (hval >>> 0).toString(16)).substr(-8);
}

/**
 * Calculate a 64 bit hash for the given input.
 * Double hash is used to significantly lower the collision probability.
 *
 * @param {string} input value to get hash for
 * @returns {string} 64 bit (as 16 byte hex string)
 */
function getHash(input) {
  let h1 = hashFnv32a(input); // returns 32 bit (as 8 byte hex string)
  return h1 + hashFnv32a(h1 + input); 
}
export const applyTheme = (target) => {
  
  injectGlobalCss(stylesCss.toString(), target);
    
  
  if (!document['_vaadintheme_carbon_componentCss']) {
    registerStyles(
      'input-button',
      unsafeCSS(inputButtonCss.toString())
    );
    registerStyles(
      'input-field',
      unsafeCSS(inputFieldCss.toString())
    );
    registerStyles(
      'vaadin-accordion-panel',
      unsafeCSS(vaadinAccordionPanelCss.toString())
    );
    registerStyles(
      'vaadin-app-layout',
      unsafeCSS(vaadinAppLayoutCss.toString())
    );
    registerStyles(
      'vaadin-button',
      unsafeCSS(vaadinButtonCss.toString())
    );
    registerStyles(
      'vaadin-checkbox-group',
      unsafeCSS(vaadinCheckboxGroupCss.toString())
    );
    registerStyles(
      'vaadin-checkbox',
      unsafeCSS(vaadinCheckboxCss.toString())
    );
    registerStyles(
      'vaadin-combo-box-item',
      unsafeCSS(vaadinComboBoxItemCss.toString())
    );
    registerStyles(
      'vaadin-combo-box-overlay',
      unsafeCSS(vaadinComboBoxOverlayCss.toString())
    );
    registerStyles(
      'vaadin-combo-box-scroller',
      unsafeCSS(vaadinComboBoxScrollerCss.toString())
    );
    registerStyles(
      'vaadin-combo-box',
      unsafeCSS(vaadinComboBoxCss.toString())
    );
    registerStyles(
      'vaadin-context-menu-item',
      unsafeCSS(vaadinContextMenuItemCss.toString())
    );
    registerStyles(
      'vaadin-context-menu-list-box',
      unsafeCSS(vaadinContextMenuListBoxCss.toString())
    );
    registerStyles(
      'vaadin-context-menu-overlay',
      unsafeCSS(vaadinContextMenuOverlayCss.toString())
    );
    registerStyles(
      'vaadin-date-picker-overlay-content',
      unsafeCSS(vaadinDatePickerOverlayContentCss.toString())
    );
    registerStyles(
      'vaadin-date-picker-text-field',
      unsafeCSS(vaadinDatePickerTextFieldCss.toString())
    );
    registerStyles(
      'vaadin-date-picker',
      unsafeCSS(vaadinDatePickerCss.toString())
    );
    registerStyles(
      'vaadin-dialog-overlay',
      unsafeCSS(vaadinDialogOverlayCss.toString())
    );
    registerStyles(
      'vaadin-dialog',
      unsafeCSS(vaadinDialogCss.toString())
    );
    registerStyles(
      'vaadin-drawer-toggle',
      unsafeCSS(vaadinDrawerToggleCss.toString())
    );
    registerStyles(
      'vaadin-grid-sorter',
      unsafeCSS(vaadinGridSorterCss.toString())
    );
    registerStyles(
      'vaadin-grid',
      unsafeCSS(vaadinGridCss.toString())
    );
    registerStyles(
      'vaadin-input-container',
      unsafeCSS(vaadinInputContainerCss.toString())
    );
    registerStyles(
      'vaadin-item',
      unsafeCSS(vaadinItemCss.toString())
    );
    registerStyles(
      'vaadin-list-box',
      unsafeCSS(vaadinListBoxCss.toString())
    );
    registerStyles(
      'vaadin-menu-bar-button',
      unsafeCSS(vaadinMenuBarButtonCss.toString())
    );
    registerStyles(
      'vaadin-menu-bar',
      unsafeCSS(vaadinMenuBarCss.toString())
    );
    registerStyles(
      'vaadin-month-calendar',
      unsafeCSS(vaadinMonthCalendarCss.toString())
    );
    registerStyles(
      'vaadin-notification-card',
      unsafeCSS(vaadinNotificationCardCss.toString())
    );
    registerStyles(
      'vaadin-number-field',
      unsafeCSS(vaadinNumberFieldCss.toString())
    );
    registerStyles(
      'vaadin-password-field-button',
      unsafeCSS(vaadinPasswordFieldButtonCss.toString())
    );
    registerStyles(
      'vaadin-password-field',
      unsafeCSS(vaadinPasswordFieldCss.toString())
    );
    registerStyles(
      'vaadin-progress-bar',
      unsafeCSS(vaadinProgressBarCss.toString())
    );
    registerStyles(
      'vaadin-radio-button',
      unsafeCSS(vaadinRadioButtonCss.toString())
    );
    registerStyles(
      'vaadin-radio-group',
      unsafeCSS(vaadinRadioGroupCss.toString())
    );
    registerStyles(
      'vaadin-select-item',
      unsafeCSS(vaadinSelectItemCss.toString())
    );
    registerStyles(
      'vaadin-select-overlay',
      unsafeCSS(vaadinSelectOverlayCss.toString())
    );
    registerStyles(
      'vaadin-select-value-button',
      unsafeCSS(vaadinSelectValueButtonCss.toString())
    );
    registerStyles(
      'vaadin-select',
      unsafeCSS(vaadinSelectCss.toString())
    );
    registerStyles(
      'vaadin-tab',
      unsafeCSS(vaadinTabCss.toString())
    );
    registerStyles(
      'vaadin-tabs',
      unsafeCSS(vaadinTabsCss.toString())
    );
    registerStyles(
      'vaadin-text-area',
      unsafeCSS(vaadinTextAreaCss.toString())
    );
    registerStyles(
      'vaadin-text-field',
      unsafeCSS(vaadinTextFieldCss.toString())
    );
    registerStyles(
      'vaadin-upload-file',
      unsafeCSS(vaadinUploadFileCss.toString())
    );
    registerStyles(
      'vaadin-upload',
      unsafeCSS(vaadinUploadCss.toString())
    );
    
    document['_vaadintheme_carbon_componentCss'] = true;
  }
  injectGlobalCss(typography.cssText, target, true);
injectGlobalCss(color.cssText, target, true);
injectGlobalCss(spacing.cssText, target, true);
injectGlobalCss(badge.cssText, target, true);
injectGlobalCss(utility.cssText, target, true);

}
