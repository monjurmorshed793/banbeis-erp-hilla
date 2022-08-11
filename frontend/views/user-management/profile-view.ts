import {View} from "Frontend/views/view";
import {customElement, state} from "lit/decorators.js";
import {html} from "lit";

@customElement('profile-view')
export class ProfileView extends View{
    @state() userId?: string;

    protected render(){
        return html`
            <h1>Hello profile</h1>
        `;
    }


    connectedCallback() {
        super.connectedCallback();


    }
}