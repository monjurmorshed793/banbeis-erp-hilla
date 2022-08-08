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
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6">Hello 1</div>
                    <div class="col-md-6">Hello 2</div>
                </div>
            </div>
        `;
    }
}