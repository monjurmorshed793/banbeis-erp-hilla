import {View} from "Frontend/views/view";
import {customElement, state} from "lit/decorators.js";
import {html} from "lit";
import {BeforeEnterObserver, RouterLocation} from '@vaadin/router'

@customElement('add-user-view')
export class AddUserView extends View implements BeforeEnterObserver{
    // @state() userId?:string;

    onBeforeEnter(location: RouterLocation){
        // this.userId = location.params['id'] as string;
        console.log(location);
    }

    protected render() {
        return html`
            <div><h1>Hello world!</h1></div>
        `;
    }
}