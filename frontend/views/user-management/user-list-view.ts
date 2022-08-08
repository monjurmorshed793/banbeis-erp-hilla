import {customElement, state} from "lit/decorators.js";
import {html} from "lit";
import { View } from '../../views/view';
import '@vaadin/vertical-layout';
import '@vaadin/horizontal-layout';
import '@vaadin/grid';
import '@vaadin/button'
import User from "Frontend/generated/bd/gov/banbeis/data/entity/User";
import {UserListEndpoint} from "Frontend/generated/endpoints";
import { GridDataProviderCallback, GridDataProviderParams} from "@vaadin/grid";
import {columnBodyRenderer, GridColumnBodyLitRenderer} from "@vaadin/grid/lit";
import { router } from "Frontend/index";
import Role from "Frontend/generated/bd/gov/banbeis/data/entity/Role";

@customElement('user-list-view')
export class UserListView extends View{

    @state() users: Array<User | undefined> | undefined = [];

    async dataProvider(params: GridDataProviderParams<any>, callback: GridDataProviderCallback<any>){
        console.log(params);
        const page = await UserListEndpoint.getPage(params.page, params.pageSize);
        callback(page?.content!, page?.size);
    }

    render(){
        return html`
            <vaadin-vertical-layout theme="spacing padding">
               <vaadin-grid .dataProvider = ${this.dataProvider}>

                </vaadin-grid-column>
                   <vaadin-grid-column path="id"></vaadin-grid-column>
                   <vaadin-grid-column path="username"></vaadin-grid-column>
                   <vaadin-grid-column path="fullName"></vaadin-grid-column>
                   <vaadin-grid-column path="email"></vaadin-grid-column>
                   <vaadin-grid-column path="roles"
                        ${columnBodyRenderer(this.roleNameRenderer, [])} 
                   ></vaadin-grid-column>
                   <vaadin-grid-column
                    frozen-to-end
                    auto-width
                    flex-grow="0"
                    ${columnBodyRenderer(this.actionRenderer, [])}
                   ></vaadin-grid-column>
               </vaadin-grid>
            </vaadin-vertical-layout>
        `;
    }

    private roleNameRenderer: GridColumnBodyLitRenderer<User> = (user)=>{
        const roles: Role[] = [];
        user.roles?.forEach((r)=> roles.push(r!));
        return html`${this.formatRoles(roles)}`;
    }

    private formatRoles(roles: Role[] ){
        const roleNameArr: string[] = [];
        roles.forEach((r)=> roleNameArr.push(r.role!));
        return roleNameArr.toString();
    }

    private actionRenderer: GridColumnBodyLitRenderer<User> = (user: User)=>{
        return html`<vaadin-button ><a href="${router.urlForPath('/add-user/'+user.id)}">Edit</a></vaadin-button>`;
    }

}