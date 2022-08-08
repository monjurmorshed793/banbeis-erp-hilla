import {View} from "Frontend/views/view";
import {customElement, state} from "lit/decorators.js";
import {html, PropertyValueMap} from "lit";
import {BeforeEnterObserver, RouterLocation} from '@vaadin/router';
import '@vaadin/form-layout';
import '@vaadin/vertical-layout';
import '@vaadin/text-field';
import '@vaadin/password-field';
import '@vaadin/email-field';
import '@vaadin/button';
import '@vaadin/horizontal-layout';
import '@vaadin/checkbox';
import '@vaadin/grid';
import '@vaadin/vaadin-grid';
import '@vaadin/grid/vaadin-grid-selection-column.js';
import '@vaadin/select';
import User from "Frontend/generated/bd/gov/banbeis/data/entity/User";
import {Binder, field} from "@hilla/form";
import UserModel from "Frontend/generated/bd/gov/banbeis/data/entity/UserModel";
import Role from "Frontend/generated/bd/gov/banbeis/data/entity/Role";
import {AddUserEndpoint} from "Frontend/generated/endpoints";
import { SelectItem } from "@vaadin/select";

@customElement('add-user-view')
export class AddUserView extends View implements BeforeEnterObserver{
    @state() userId?:string;
    @state() user?: User;
    @state() roles: Role[] = [];
    @state() selectedRoles: Role[] = [];
    private binder = new Binder(this, UserModel);

    onBeforeEnter(location: RouterLocation){
        this.userId = location.params['id'] as string;
        if(this.userId){
            this.getUserInformation();
        }
    }

    protected render() {
        const {model} = this.binder;
        
        return html`
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <vaadin-vertical-layout>
                            <vaadin-form-layout>
                                <vaadin-text-field label="Full Name" ${field(model.fullName)}></vaadin-text-field>
                                <vaadin-text-field label="Email" ${field(model.email)}></vaadin-text-field>
                                <vaadin-password-field label="Password" ${field(model.password)}></vaadin-password-field>
                                <vaadin-password-field label="Confirm Password" ${field(model.confirmPassword)}></vaadin-password-field>
                            </vaadin-form-layout>
                            
                            <vaadin-grid .items = "${this.roles}" all-rows-visible .selectedItems="${this.selectedRoles}" aria-label="Roles">
                                <vaadin-grid-selection-column></vaadin-grid-selection-column>
                                <vaadin-grid-column path="role">Roles</vaadin-grid-column>
                            </vaadin-grid>


                            <vaadin-horizontal-layout>
                                <vaadin-button theme="primary">Save</vaadin-button>
                                <vaadin-button theme="danger">Cancel</vaadin-button>
                            </vaadin-horizontal-layout>

                        </vaadin-vertical-layout>
                    </div>
                </div>
            </div>
        `;
    }

    async getUserInformation(){
        this.user = await AddUserEndpoint.getUserById(this.userId);       
    }

    async connectedCallback(){
        super.connectedCallback();
    }

    async firstUpdated() {
        this.roles = await AddUserEndpoint.getRoles();
        console.log(this.roles);
    }
}