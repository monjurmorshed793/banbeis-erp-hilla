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
    @state() roles: Role[] = [];
    @state() items: SelectItem[] = [];
    private binder = new Binder(this, UserModel);

    onBeforeEnter(location: RouterLocation){
        // this.userId = location.params['id'] as string;
        console.log(location);
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
                                <vaadin-select label="Roles" .items="${this.items}" ></vaadin-select>
                                <vaadin-password-field label="Password" ${field(model.password)}></vaadin-password-field>
                                <vaadin-password-field label="Confirm Password" ${field(model.confirmPassword)}></vaadin-password-field>
                            </vaadin-form-layout>
                            <vaadin-horizontal-layout>
                                <vaadin-button theme="primary">Save</vaadin-button>
                            </vaadin-horizontal-layout>
                        </vaadin-vertical-layout>
                    </div>
                </div>
            </div>
        `;
    }

    async connectedCallback(){
        super.connectedCallback();
    }

    async firstUpdated() {
        this.roles = await AddUserEndpoint.getRoles();
        this.items = this.roles.map((role)=>{
            return {
                label: `${role.role}`,
                value: `${role.id}`
            }
        });
    }
}