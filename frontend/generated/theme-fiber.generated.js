import 'construct-style-sheets-polyfill';
import { DomModule } from "@polymer/polymer/lib/elements/dom-module";
import { stylesFromTemplate } from "@polymer/polymer/lib/utils/style-gather";
import { css, unsafeCSS, registerStyles } from '@vaadin/vaadin-themable-mixin/register-styles';

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

const addCssBlock = function (block, before = false) {
  const tpl = document.createElement("template");
  tpl.innerHTML = block;
  document.head[before ? "insertBefore" : "appendChild"](
    tpl.content,
    document.head.firstChild
  );
};

const addStyleInclude = (module, target) => {
  addCssBlock(`<custom-style><style include="${module}"></style></custom-style>`, true);
};

const getStyleModule = (id) => {
  const template = DomModule.import(id, "template");
  const cssText =
    template &&
    stylesFromTemplate(template, "")
      .map((style) => style.textContent)
      .join(" ");
  return cssText;
};
import stylesCss from 'themes/fiber/styles.css';
import '@vaadin/vaadin-lumo-styles/typography.js';
import '@vaadin/vaadin-lumo-styles/color.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/badge.js';
import inputButtonCss from 'themes/fiber/components/input-button.css';
import inputFieldCss from 'themes/fiber/components/input-field.css';
import inputLabelCss from 'themes/fiber/components/input-label.css';
import vaadinAccordionPanelCss from 'themes/fiber/components/vaadin-accordion-panel.css';
import vaadinAccordionCss from 'themes/fiber/components/vaadin-accordion.css';
import vaadinAppLayoutCss from 'themes/fiber/components/vaadin-app-layout.css';
import vaadinButtonCss from 'themes/fiber/components/vaadin-button.css';
import vaadinCheckboxGroupCss from 'themes/fiber/components/vaadin-checkbox-group.css';
import vaadinCheckboxCss from 'themes/fiber/components/vaadin-checkbox.css';
import vaadinComboBoxItemCss from 'themes/fiber/components/vaadin-combo-box-item.css';
import vaadinComboBoxOverlayCss from 'themes/fiber/components/vaadin-combo-box-overlay.css';
import vaadinComboBoxCss from 'themes/fiber/components/vaadin-combo-box.css';
import vaadinContextMenuItemCss from 'themes/fiber/components/vaadin-context-menu-item.css';
import vaadinContextMenuListBoxCss from 'themes/fiber/components/vaadin-context-menu-list-box.css';
import vaadinContextMenuOverlayCss from 'themes/fiber/components/vaadin-context-menu-overlay.css';
import vaadinDatePickerTextFieldCss from 'themes/fiber/components/vaadin-date-picker-text-field.css';
import vaadinDatePickerCss from 'themes/fiber/components/vaadin-date-picker.css';
import vaadinDialogOverlayCss from 'themes/fiber/components/vaadin-dialog-overlay.css';
import vaadinDialogCss from 'themes/fiber/components/vaadin-dialog.css';
import vaadinDrawerToggleCss from 'themes/fiber/components/vaadin-drawer-toggle.css';
import vaadinGridSorterCss from 'themes/fiber/components/vaadin-grid-sorter.css';
import vaadinGridCss from 'themes/fiber/components/vaadin-grid.css';
import vaadinItemCss from 'themes/fiber/components/vaadin-item.css';
import vaadinListBoxCss from 'themes/fiber/components/vaadin-list-box.css';
import vaadinMenuBarButtonCss from 'themes/fiber/components/vaadin-menu-bar-button.css';
import vaadinMenuBarCss from 'themes/fiber/components/vaadin-menu-bar.css';
import vaadinNotificationCardCss from 'themes/fiber/components/vaadin-notification-card.css';
import vaadinNumberFieldCss from 'themes/fiber/components/vaadin-number-field.css';
import vaadinPasswordFieldCss from 'themes/fiber/components/vaadin-password-field.css';
import vaadinProgressBarCss from 'themes/fiber/components/vaadin-progress-bar.css';
import vaadinRadioButtonCss from 'themes/fiber/components/vaadin-radio-button.css';
import vaadinRadioGroupCss from 'themes/fiber/components/vaadin-radio-group.css';
import vaadinSelectOverlayCss from 'themes/fiber/components/vaadin-select-overlay.css';
import vaadinSelectCss from 'themes/fiber/components/vaadin-select.css';
import vaadinTabCss from 'themes/fiber/components/vaadin-tab.css';
import vaadinTabsCss from 'themes/fiber/components/vaadin-tabs.css';
import vaadinTextAreaCss from 'themes/fiber/components/vaadin-text-area.css';
import vaadinTextFieldCss from 'themes/fiber/components/vaadin-text-field.css';
import vaadinUploadFileCss from 'themes/fiber/components/vaadin-upload-file.css';
import vaadinUploadCss from 'themes/fiber/components/vaadin-upload.css';

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
    
  
  if (!document['_vaadintheme_fiber_componentCss']) {
    registerStyles(
      'input-button',
      css`
        ${unsafeCSS(inputButtonCss.toString())}
      `
    );
    registerStyles(
      'input-field',
      css`
        ${unsafeCSS(inputFieldCss.toString())}
      `
    );
    registerStyles(
      'input-label',
      css`
        ${unsafeCSS(inputLabelCss.toString())}
      `
    );
    registerStyles(
      'vaadin-accordion-panel',
      css`
        ${unsafeCSS(vaadinAccordionPanelCss.toString())}
      `
    );
    registerStyles(
      'vaadin-accordion',
      css`
        ${unsafeCSS(vaadinAccordionCss.toString())}
      `
    );
    registerStyles(
      'vaadin-app-layout',
      css`
        ${unsafeCSS(vaadinAppLayoutCss.toString())}
      `
    );
    registerStyles(
      'vaadin-button',
      css`
        ${unsafeCSS(vaadinButtonCss.toString())}
      `
    );
    registerStyles(
      'vaadin-checkbox-group',
      css`
        ${unsafeCSS(vaadinCheckboxGroupCss.toString())}
      `
    );
    registerStyles(
      'vaadin-checkbox',
      css`
        ${unsafeCSS(vaadinCheckboxCss.toString())}
      `
    );
    registerStyles(
      'vaadin-combo-box-item',
      css`
        ${unsafeCSS(vaadinComboBoxItemCss.toString())}
      `
    );
    registerStyles(
      'vaadin-combo-box-overlay',
      css`
        ${unsafeCSS(vaadinComboBoxOverlayCss.toString())}
      `
    );
    registerStyles(
      'vaadin-combo-box',
      css`
        ${unsafeCSS(vaadinComboBoxCss.toString())}
      `
    );
    registerStyles(
      'vaadin-context-menu-item',
      css`
        ${unsafeCSS(vaadinContextMenuItemCss.toString())}
      `
    );
    registerStyles(
      'vaadin-context-menu-list-box',
      css`
        ${unsafeCSS(vaadinContextMenuListBoxCss.toString())}
      `
    );
    registerStyles(
      'vaadin-context-menu-overlay',
      css`
        ${unsafeCSS(vaadinContextMenuOverlayCss.toString())}
      `
    );
    registerStyles(
      'vaadin-date-picker-text-field',
      css`
        ${unsafeCSS(vaadinDatePickerTextFieldCss.toString())}
      `
    );
    registerStyles(
      'vaadin-date-picker',
      css`
        ${unsafeCSS(vaadinDatePickerCss.toString())}
      `
    );
    registerStyles(
      'vaadin-dialog-overlay',
      css`
        ${unsafeCSS(vaadinDialogOverlayCss.toString())}
      `
    );
    registerStyles(
      'vaadin-dialog',
      css`
        ${unsafeCSS(vaadinDialogCss.toString())}
      `
    );
    registerStyles(
      'vaadin-drawer-toggle',
      css`
        ${unsafeCSS(vaadinDrawerToggleCss.toString())}
      `
    );
    registerStyles(
      'vaadin-grid-sorter',
      css`
        ${unsafeCSS(vaadinGridSorterCss.toString())}
      `
    );
    registerStyles(
      'vaadin-grid',
      css`
        ${unsafeCSS(vaadinGridCss.toString())}
      `
    );
    registerStyles(
      'vaadin-item',
      css`
        ${unsafeCSS(vaadinItemCss.toString())}
      `
    );
    registerStyles(
      'vaadin-list-box',
      css`
        ${unsafeCSS(vaadinListBoxCss.toString())}
      `
    );
    registerStyles(
      'vaadin-menu-bar-button',
      css`
        ${unsafeCSS(vaadinMenuBarButtonCss.toString())}
      `
    );
    registerStyles(
      'vaadin-menu-bar',
      css`
        ${unsafeCSS(vaadinMenuBarCss.toString())}
      `
    );
    registerStyles(
      'vaadin-notification-card',
      css`
        ${unsafeCSS(vaadinNotificationCardCss.toString())}
      `
    );
    registerStyles(
      'vaadin-number-field',
      css`
        ${unsafeCSS(vaadinNumberFieldCss.toString())}
      `
    );
    registerStyles(
      'vaadin-password-field',
      css`
        ${unsafeCSS(vaadinPasswordFieldCss.toString())}
      `
    );
    registerStyles(
      'vaadin-progress-bar',
      css`
        ${unsafeCSS(vaadinProgressBarCss.toString())}
      `
    );
    registerStyles(
      'vaadin-radio-button',
      css`
        ${unsafeCSS(vaadinRadioButtonCss.toString())}
      `
    );
    registerStyles(
      'vaadin-radio-group',
      css`
        ${unsafeCSS(vaadinRadioGroupCss.toString())}
      `
    );
    registerStyles(
      'vaadin-select-overlay',
      css`
        ${unsafeCSS(vaadinSelectOverlayCss.toString())}
      `
    );
    registerStyles(
      'vaadin-select',
      css`
        ${unsafeCSS(vaadinSelectCss.toString())}
      `
    );
    registerStyles(
      'vaadin-tab',
      css`
        ${unsafeCSS(vaadinTabCss.toString())}
      `
    );
    registerStyles(
      'vaadin-tabs',
      css`
        ${unsafeCSS(vaadinTabsCss.toString())}
      `
    );
    registerStyles(
      'vaadin-text-area',
      css`
        ${unsafeCSS(vaadinTextAreaCss.toString())}
      `
    );
    registerStyles(
      'vaadin-text-field',
      css`
        ${unsafeCSS(vaadinTextFieldCss.toString())}
      `
    );
    registerStyles(
      'vaadin-upload-file',
      css`
        ${unsafeCSS(vaadinUploadFileCss.toString())}
      `
    );
    registerStyles(
      'vaadin-upload',
      css`
        ${unsafeCSS(vaadinUploadCss.toString())}
      `
    );
    
    document['_vaadintheme_fiber_componentCss'] = true;
  }
  // Lumo styles are injected into shadow roots.
// For the document, we need to be compatible with flow-generated-imports and add missing <style> tags.
const shadowRoot = (target instanceof ShadowRoot);
if (shadowRoot) {
injectGlobalCss(getStyleModule("lumo-typography"), target, true);
injectGlobalCss(getStyleModule("lumo-color"), target, true);
injectGlobalCss(getStyleModule("lumo-spacing"), target, true);
injectGlobalCss(getStyleModule("lumo-badge"), target, true);
} else if (!document['_vaadinthemelumoimports_']) {
addStyleInclude("lumo-typography", target);
addStyleInclude("lumo-color", target);
addStyleInclude("lumo-spacing", target);
addStyleInclude("lumo-badge", target);
document['_vaadinthemelumoimports_'] = true;
}

}
