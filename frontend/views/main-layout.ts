import '@vaadin-component-factory/vcf-nav';
import '@vaadin/app-layout';
import { AppLayout } from '@vaadin/app-layout';
import '@vaadin/app-layout/vaadin-drawer-toggle';
import '@vaadin/avatar/vaadin-avatar';
import '@vaadin/context-menu';
import '@vaadin/tabs';
import '@vaadin/tabs/vaadin-tab';
import '@vaadin/accordion';
import 'bootstrap/dist/js/bootstrap'
import { html, render } from 'lit';
import { customElement } from 'lit/decorators.js';
import { logout } from '../auth';
import { router } from '../index';
import { hasAccess, views } from '../routes';
import { appStore } from '../stores/app-store';
import { Layout } from './view';
import "@vaadin/accordion/src/vaadin-accordion-panel";

interface RouteInfo {
  path: string;
  title: string;
  icon: string;
}

@customElement('main-layout')
export class MainLayout extends Layout {
  render() {
    return html`
      <vaadin-app-layout primary-section="drawer">
        <header class="view-header" slot="navbar">
          <vaadin-drawer-toggle aria-label="Menu toggle" class="view-toggle" theme="contrast"></vaadin-drawer-toggle>
          <h2 class="view-title">${appStore.currentViewTitle}</h2>
        </header>
          
        <section class="drawer-section" slot="drawer" >
          <h1 class="app-name">BANBEIS ERP</h1>
          
          <vaadin-accordion-panel theme="reverse">
            <div slot="summary">Employee Management</div>
            <vaadin-vertical-layout>
            <vcf-nav id="user-management" [label]="user management"  class="app-nav" aria-label="User Management" label="User management" collapsible="true" collapsed="true">
            ${this.getMenuRoutes().map(
              (viewRoute) => html`
                <vcf-nav-item expanded="false" path=${router.urlForPath(viewRoute.path)}>
                  <span class="${viewRoute.icon} nav-item-icon" slot="prefix" aria-hidden="true"></span>
                  ${viewRoute.title}
                </vcf-nav-item>
              `
            )}
          </vcf-nav>  
          </vaadin-vertical-layout>
        </vaadin-accordion-panel>
          </vaadin-accordion-panel>

            
          <footer class="app-nav-footer">
            ${appStore.user
              ? html` <vaadin-context-menu open-on="click" .renderer="${this.renderLogoutOptions}">
                  <vaadin-avatar
                    theme="xsmall"
                    img="${appStore.user.profilePictureUrl}"
                    name="${appStore.user.fullName}"
                  ></vaadin-avatar>
                  <span class="font-medium ms-xs text-s text-secondary">${appStore.user.fullName}</span>
                </vaadin-context-menu>`
              : html`<a router-ignore href="login">Sign in</a>`}
          </footer>
        </section>
        <slot></slot>
      </vaadin-app-layout>
    `;
  }

  connectedCallback() {
    super.connectedCallback();
    this.classList.add('block', 'h-full');
    this.reaction(
      () => appStore.location,
      () => {
        AppLayout.dispatchCloseOverlayDrawerEvent();
      }
    );
  }

  private renderLogoutOptions(root: HTMLElement) {
    render(html`<vaadin-list-box><vaadin-item @click=${() => logout()}>Logout</vaadin-item></vaadin-list-box>`, root);
  }

  private getMenuRoutes(): RouteInfo[] {
    return views.filter((route) => route.title).filter((route) => hasAccess(route)) as RouteInfo[];
  }
}
