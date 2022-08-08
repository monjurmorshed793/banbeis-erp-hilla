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
import '@vaadin/notification';
import User from "Frontend/generated/bd/gov/banbeis/data/entity/User";
import {Binder, field} from "@hilla/form";
import UserModel from "Frontend/generated/bd/gov/banbeis/data/entity/UserModel";
import Role from "Frontend/generated/bd/gov/banbeis/data/entity/Role";
import {AddUserEndpoint} from "Frontend/generated/endpoints";
import { GridActiveItemChangedEvent } from "@vaadin/grid";
import { Notification } from "@vaadin/notification";
import { FormLayoutResponsiveStep } from "@vaadin/form-layout";

@customElement('add-user-view')
export class AddUserView extends View implements BeforeEnterObserver{
    @state() userId?:string;
    @state() user?: User;
    @state() roles: Role[] = [];
    @state() selectedRoles?: Role[] = [];
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
                                <vaadin-text-field label="Full Name" ${field(model.fullName)} colspan="2"></vaadin-text-field>
                                <vaadin-text-field label="User Name" ${field(model.username)}></vaadin-text-field>
                                <vaadin-text-field label="Email" ${field(model.email)}></vaadin-text-field>
                                <vaadin-password-field label="Password" ${field(model.password)} colspan="2"></vaadin-password-field>
                                <vaadin-password-field label="Confirm Password" ${field(model.confirmPassword)} colspan="2"></vaadin-password-field>
                            </vaadin-form-layout>
                            
                            <vaadin-grid .items = "${this.roles}" 
                                all-rows-visible 
                                .selectedItems="${this.selectedRoles!}" 
                                aria-label="Roles"
                                @selected-items-changed = "${(e: GridActiveItemChangedEvent<Role[]>)=>{
                                    const selectedItem = e.detail.value;
                                    this.selectedRoles = selectedItem;
                                }}"
                                >
                                <vaadin-grid-selection-column ></vaadin-grid-selection-column>
                                <vaadin-grid-column path="role">Roles</vaadin-grid-column>
                            </vaadin-grid>


                            <vaadin-horizontal-layout>
                                <vaadin-button theme="primary" @click=${this.saveOrUpdateUser}>Save</vaadin-button>
                                <vaadin-button theme="danger" @click=${this.cancel}>Cancel</vaadin-button>
                            </vaadin-horizontal-layout>

                        </vaadin-vertical-layout>
                    </div>
                </div>
            </div>
        `;
    }

    async getUserInformation(){
        this.user = await AddUserEndpoint.getUserById(this.userId); 
        this.binder.read(this.user);
        this.configureSelectedRoles();

        setTimeout(()=>{
            this.configureSelectedRoles();
        }, 1000);
    }

    private  configureSelectedRoles(){
        this.selectedRoles = [];  
        let userRoleIds: string[] = [];
        this.user?.roles?.forEach(r=> userRoleIds.push(r!.id!));

        let roleIds: string[] = [];
        let roleMap: Map<string, Role> = new Map();
        this.roles.forEach(r=> {
            roleIds.push(r.id!);
            roleMap.set(r.id!, r);
        });
        const matchedIds: string[] = roleIds.filter(element=> userRoleIds.includes(element));  // this match is required, else object equality is not reflected.
        matchedIds.forEach(id=> this.selectedRoles?.push(roleMap.get(id)!));
    }

    async connectedCallback(){
        super.connectedCallback();
    }

    async firstUpdated() {
        this.fetchAllRoles();
    }

    private async fetchAllRoles(){
        this.roles = await AddUserEndpoint.getRoles();
    }

    async saveOrUpdateUser(){
        if(this.binder.value.password !== this.binder.value.confirmPassword){
            Notification.show("Password and Confirm password is needed to be same", {
                position: 'middle'
            });
        }else{
            this.user = this.binder.value;
            this.user.roles = this.selectedRoles;
            this.binder.read(this.user);
            this.binder.submitTo(AddUserEndpoint.saveUser).then(()=>{
                this.binder.clear();
                if(this.userId){
                    window.history.back();
                }
            })
        }

    }

    cancel(){
        this.binder.clear();
    }
    
}